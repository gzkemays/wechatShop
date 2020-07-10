package com.myself.supermarket.utils;

import com.myself.supermarket.mapper.TOrderMapper;
import com.myself.supermarket.mapper.TWechatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
@Component
public class GetAllMessageCount {
    private static TWechatMapper wechatMapper;
    @Autowired
    public void setWechatMapper(TWechatMapper wechatMapper){GetAllMessageCount.wechatMapper = wechatMapper;}

    private static TOrderMapper orderMapper;
    @Autowired
    public void setOrderMapper(TOrderMapper orderMapper){GetAllMessageCount.orderMapper = orderMapper;}

    public static void getAllCountModel(Model model){
        System.out.println("wechatMapper.selectCount(null) = " + wechatMapper.selectCount(null));
        model.addAttribute("chatCount",wechatMapper.selectCount(null));
        model.addAttribute("orderCount",orderMapper.selectCount(null));
    }

}
