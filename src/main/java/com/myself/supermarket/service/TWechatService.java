package com.myself.supermarket.service;

import com.myself.supermarket.pojo.TWechat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzkemays
 * @since 2020-07-02
 */
public interface TWechatService extends IService<TWechat> {
    void adminSend(TWechat wechat);
    void userSend(TWechat wechat);
    List<TWechat> getChat(Long id);
}
