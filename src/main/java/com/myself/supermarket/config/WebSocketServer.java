package com.myself.supermarket.config;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myself.supermarket.pojo.TWechat;
import com.myself.supermarket.service.TWechatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * WebSocketServer
 * @author gzkemays
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketServer {

    private static TWechatService wechatService;
    @Autowired
    public void setWechatService(TWechatService wechatService){
        WebSocketServer.wechatService = wechatService;
    }
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        System.out.println("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("{'tag':'ok'}");
        } catch (IOException e) {
            System.out.println("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        System.out.println("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("用户消息:"+userId+",报文:"+message);
            System.out.println("this:"+this+"客户端信息:"+message);
                System.out.println("web = " + webSocketMap.get(userId ));
                webSocketMap.get(userId).session.getAsyncRemote().sendText(message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId",this.userId);
                String toUserId = jsonObject.getString("toUserId");
                String chatMessage = jsonObject.getString("contentText");
                System.out.println("chatMessage = " + chatMessage);
                TWechat wechat = new TWechat();
                wechat.setChatHistory(chatMessage);
                wechat.setAdminId(1L);

                // 判断存储数据库
                // 管理员发送信息：toUserId 不能为 1，他的 id 必须为 1
                // 传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                    System.out.println("webSocketMap:"+webSocketMap);
                    if(userId.equals("1")&&toUserId!="1"){
                        wechat.setPower(0);
                        wechat.setUserId(Long.parseLong(toUserId));
                        wechatService.adminSend(wechat);
                    }else{
                        wechat.setPower(1);
                        wechat.setUserId(Long.parseLong(userId));
                        wechatService.userSend(wechat);
                    }
                }else{
                    //否则不在这个服务器上
                    System.out.println("请求的userId:"+toUserId+"不在该服务器上");

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        System.out.println("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            System.out.println("用户"+userId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}