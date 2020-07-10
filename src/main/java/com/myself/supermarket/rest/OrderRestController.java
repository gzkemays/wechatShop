package com.myself.supermarket.rest;

import com.myself.supermarket.pojo.TOrder;
import com.myself.supermarket.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRestController {
    @Autowired
    private TOrderService orderService;
    @GetMapping("/restOrders/{id}")
    public List<TOrder> getUserOrders(@PathVariable("id") Long id){
        return orderService.getOrderByUser(id);
    }
}
