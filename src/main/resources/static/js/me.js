let messNum = parseInt($(".circular.label.mess").text())
let orderNum = parseInt($(".circular.label.order").text())
let commNum = parseInt($(".circular.label.comm").text())

judgeCircular(messNum,$(".circular.label.mess"))
judgeCircular(orderNum,$(".circular.label.order"))
judgeCircular(commNum,$(".circular.label.comm"))
function judgeCircular(num,that){
    if(that.length>1){
        for(let i = 0;i<=that.length;i++){
            num = that.eq(i).text()
            cssAndText(num,that.eq(i))
        }      
    }else{
        cssAndText(num,that)
    }
    function cssAndText(num,that){
        if(num > 50){
            that.addClass("red")
        }else if(num >=30 && num<=50){
            that.addClass("orange")
        }
        if(num>99){
            that.text("99+")
        }
    }    
}



var socket;
function openSocket() {
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else {
        console.log("您的浏览器支持WebSocket");
        var socketUrl = "http://localhost:8080/websocket/1";
        socketUrl = socketUrl.replace("https","ws").replace("http","ws");
        console.log(socketUrl);
        // 统计获取的消息条目
        if(socket != null){
            socket.close();
            socket = null;
        }else{
            socket = new WebSocket(socketUrl);
            socket.onmessage = function(msg){
                console.log(msg.data);
                if( (msg.data).lastIndexOf("ok") == -1 ){
                    let oldCount = $($(".circular.label.mess")).text()
                    $($(".circular.label.mess")).text(parseInt(oldCount)+1)
                    judgeCircular(commNum,$(".circular.label.mess"));
                }

            }
            socket.onclose = function(msg){
                console.log(msg)
            }
        }
    }

}
function closeSocket(){
    socket.close();
}
