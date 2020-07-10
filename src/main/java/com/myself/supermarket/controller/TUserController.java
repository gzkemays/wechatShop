package com.myself.supermarket.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TUser;
import com.myself.supermarket.service.TUserService;
import com.myself.supermarket.utils.GetAllMessageCount;
import com.myself.supermarket.utils.WechatClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Controller
public class TUserController {
@Autowired
private TUserService userService;

    @GetMapping("/login")
    @ResponseBody
    public Long userLogin(String openid, String info){
        Long id = userService.getUserLoginTag(openid,info);
        return id;
    }



    @GetMapping("/loginOpenId")
    @ResponseBody
    public String returnOpenId(String code){
        String openId = userService.returnOpenId(code);
        return openId;
    }

    @GetMapping("/message")
    public String getMessagePage(Model model, Page<TUser> page){
        GetAllMessageCount.getAllCountModel(model);
        page.setSize(5);
        model.addAttribute("userList",userService.getUserPage(page));
        GetAllMessageCount.getAllCountModel(model);
        return "message";
    }



}

