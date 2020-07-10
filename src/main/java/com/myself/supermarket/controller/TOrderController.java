package com.myself.supermarket.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TOrder;
import com.myself.supermarket.pojo.TProduct;
import com.myself.supermarket.service.TOrderService;
import com.myself.supermarket.service.TProductService;
import com.myself.supermarket.utils.GetAllMessageCount;
import com.myself.supermarket.vo.AddProductVO;
import com.myself.supermarket.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TOrderController {
    @Autowired
    private TOrderService orderService;
    @Autowired
    private TProductService productService;
    public static final String ORDER = "order";
    public static final String ORDER_REDIRECT = "redirect:/order";
    public static final String ORDER_FRAGMENT_BLOCK = "order::searchList";
    @GetMapping("/order")
    public String getOrderPage(Model model, Page<TOrder>page){
        page.setSize(5);
        GetAllMessageCount.getAllCountModel(model);
        model.addAttribute("allOrder",orderService.getAllOrder(page));
        return ORDER;
    }
    @GetMapping("/addOrder")
    @ResponseBody
    public String addOrder(TOrder order,OrderVO vo){
        System.out.println("order = " + order);
        System.out.println("vo = " + vo);
        orderService.addOrder(order,vo);
        return "ok";
    }

    @GetMapping("/sendOrder/{id}")
    public String sendOrder(TOrder order){
        orderService.sendOrder(order);
        return ORDER_REDIRECT;
    }

    @GetMapping("/po/{id}")
    @ResponseBody
    public Map<Object,Object> getProductByOrder(@PathVariable("id") Long id,TOrder order){
        Map<Object,Object> map = new HashMap<>();
        map.put("order",orderService.getBaseMapper().selectById(id));
        map.put("product",productService.getProductByOrder(order));
        return map;
    }

    @PostMapping("/searchOrder")
    public String searchOrder(Model model,OrderVO vo,Page<TOrder>page){
        System.out.println("vo = " + vo);
        page.setSize(5);
        GetAllMessageCount.getAllCountModel(model);
        model.addAttribute("allOrder",orderService.searchOrderOrUser(page,vo));
        return ORDER_FRAGMENT_BLOCK;
    }
}

