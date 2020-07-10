package com.myself.supermarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
public interface TUserService extends IService<TUser> {
    Long getUserLoginTag(String code, String info);
    Page<TUser> getUserPage(Page<TUser> page);
    String returnOpenId(String code);
    Long returnUserId(String openid);
}
