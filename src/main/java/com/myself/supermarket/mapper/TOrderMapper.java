package com.myself.supermarket.mapper;

import com.myself.supermarket.pojo.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TableGenerator;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Repository
public interface TOrderMapper extends BaseMapper<TOrder> {
    @Insert("insert into t_product_users (products_id , users_id) value (#{pId},#{uId})")
    void addPU(@Param("pId") Long pId, @Param("uId") Long uId);
    @Insert("insert into t_product_orders (product_id , orders_id) value (#{pId},#{oId})")
    void addPO(@Param("pId") Long pId, @Param("oId") Long uId);
    @Insert("insert into t_user_orders (user_id , orders_id) value (#{uId},#{oId})")
    void addUO(@Param("uId") Long uId, @Param("oId") Long oId);
}
