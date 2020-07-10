package com.myself.supermarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myself.supermarket.pojo.TProduct;
import com.myself.supermarket.pojo.TUser;
import com.myself.supermarket.vo.AddProductVO;
import com.myself.supermarket.vo.OrderVO;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
public interface TOrderService extends IService<TOrder> {
    boolean addOrder(TOrder order, OrderVO vo);
    Page<TOrder> getAllOrder(Page<TOrder>page);
    boolean sendOrder(TOrder order);
    Page<TOrder> searchOrderOrUser(Page<TOrder>page, OrderVO vo);
    List<TOrder> getOrderByUser(Long id);
}
