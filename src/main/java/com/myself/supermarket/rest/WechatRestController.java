package com.myself.supermarket.rest;

import com.myself.supermarket.pojo.TWechat;
import com.myself.supermarket.service.TWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WechatRestController {
    @Autowired
    private TWechatService wechatService;
    @GetMapping("/restWechat/{id}")
    public List<TWechat> getHistory(@PathVariable("id")Long id){
        return wechatService.getChat(id);
    }
}
