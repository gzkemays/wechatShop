package com.myself.supermarket.controller;


import com.myself.supermarket.pojo.TWechat;
import com.myself.supermarket.service.TUserService;
import com.myself.supermarket.service.TWechatService;
import com.myself.supermarket.utils.GetAllMessageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzkemays
 * @since 2020-07-02
 */
@Controller
public class TWechatController {
    @Autowired
    private TWechatService wechatService;
    @Autowired
    private TUserService userService;

    public static final String WECHAT = "wechat";
    @GetMapping("/wechat/{id}")
    public String getWechatPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("allChat",wechatService.getChat(id));
        model.addAttribute("userMessage",userService.getBaseMapper().selectById(id));

        return WECHAT;
    }

    @PostMapping("/adminSend")
    public String sendWechat(TWechat wechat){
        System.out.println("wechat = " + wechat);
        wechatService.adminSend(wechat);
        return WECHAT;
    }

    @PostMapping("/userSend")
    public String sendWechat2(TWechat wechat){
        wechatService.userSend(wechat);
        return WECHAT;
    }
}

