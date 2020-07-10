package com.myself.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.mapper.TProductMapper;
import com.myself.supermarket.mapper.TUserMapper;
import com.myself.supermarket.pojo.TOrder;
import com.myself.supermarket.mapper.TOrderMapper;
import com.myself.supermarket.pojo.TProduct;
import com.myself.supermarket.pojo.TUser;
import com.myself.supermarket.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.supermarket.utils.OrderNumGenerate;
import com.myself.supermarket.vo.AddProductVO;
import com.myself.supermarket.vo.OrderVO;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Service
@Transactional
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
@Autowired
private TOrderMapper orderMapper;
@Autowired
private TUserMapper userMapper;
@Autowired
private TProductMapper productMapper;
    @Override
    public boolean addOrder(TOrder order, OrderVO vo) {
        order.setOrderNumber(Long.valueOf(OrderNumGenerate.getOrderNoByUUID()).longValue());
        orderMapper.insert(order);
        arrayConverterAdd(order, stringToArray(vo.getProductsNum(),vo.getProductsId()));
        return true;
    }

    @Override
    public Page<TOrder> getAllOrder(Page<TOrder> page) {
        Page<TOrder> tOrderPage = orderMapper.selectPage(page, null);
        for(TOrder order:tOrderPage.getRecords()){
            order.setUser(getUserByOrder(order.getId()));
        }
        return tOrderPage;
    }

    @Override
    public boolean sendOrder(TOrder order) {
        boolean status = orderMapper.selectById(order).isUsed();
        if(!status){
            order.setUsed(true);
            orderMapper.updateById(order);
            return true;
        }
        return false;
    }

    @Override
    public Page<TOrder> searchOrderOrUser(Page<TOrder> page,OrderVO vo) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper();
        Page<TOrder> orders = new Page<>();
        /** 一个用户允许有多个订单，因此需要分页查询 */
        if(vo.getUsername() != null){
            TUser user = getUserIdByName(vo);
            if (user != null){
                wrapper.eq("user_id",user.getId());
                orders = orderMapper.selectPage(page,wrapper);
                for (TOrder order : orders.getRecords()){
                    order.setUser(user);
                }
            }
         /** 订单是唯一的因此精准查询 */
        }else if(vo.getOrderNumber() != null){
            wrapper.eq("order_number",vo.getOrderNumber());
            orders = orderMapper.selectPage(page,wrapper);
            for (TOrder o : orders.getRecords()){
                o.setUser(getUserByOrder(o.getId()));
            }
        }else{
            orders = getAllOrder(page);
        }
        return orders;
    }

    @Override
    public List<TOrder> getOrderByUser(Long id) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id).orderByDesc("update_time");
        return orderMapper.selectList(wrapper);
    }

    /** 插入订单表的关联表的数据 */
    private void arrayConverterAdd(TOrder order, Map map){
        ArrayList<Long> idAndNum = new ArrayList<>();
        String[] numberArr = (String[]) map.get("number");
        String[] products = (String[]) map.get("productsId");
        for (int i = 0 ; i < numberArr.length; i++){
            // 获取商品的 id
            for (int j = 0 ; j<Integer.parseInt(numberArr[i]); j++){
                idAndNum.add(Long.valueOf(products[i]).longValue());
            }
        }
        for (Long id : idAndNum){
            orderMapper.addPO(id,order.getId());
            orderMapper.addPU(id,order.getUserId());
        }
        orderMapper.addUO(order.getUserId(),order.getId());
    }

    /** 获取订单对应的用户 */
    private TUser getUserByOrder(Long id){
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper .inSql("id","select user_id from t_user_orders where orders_id="+id)
                .select("username");
        return userMapper.selectOne(wrapper);
    }

    /** 返回用户的 id  */
    private TUser getUserIdByName(OrderVO vo){
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",vo.getUsername());
        return userMapper.selectOne(wrapper);
    }

    /** String 转数组*/
    private Map<String[],Object> stringToArray(String productsNum,String productsId){
        String one = productsNum.replace("[","").replace("]","");
        String two = productsId.replace("[","").replace("]","");
        String[] arrNum = one.split(",");
        String[] arrId = two.split(",");
        Map map = new HashMap();
        map.put("number",arrNum);
        map.put("productsId",arrId);
        return map;
    }
}
