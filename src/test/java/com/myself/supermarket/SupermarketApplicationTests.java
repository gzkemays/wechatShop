//package com.myself.supermarket;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.myself.supermarket.mapper.*;
//import com.myself.supermarket.pojo.*;
//import com.myself.supermarket.service.TParentTypeService;
//import com.myself.supermarket.service.TWechatService;
//import com.myself.supermarket.vo.SearchTypeVO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//class SupermarketApplicationTests {
//    @Autowired
//    private TTypeMapper typeMapper;
//    @Autowired
//    private TParentTypeMapper ptMapper;
//    @Autowired
//    private TOrderMapper orderMapper;
//    @Test
//    void contextLoads() {
//        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
//        wrapper.eq("id",9L);
//        TOrder order = orderMapper.selectOne(wrapper);
//        System.out.println("order.isSend_status() = " + order);
//    }
//}
