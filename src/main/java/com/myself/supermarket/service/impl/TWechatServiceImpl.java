package com.myself.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myself.supermarket.pojo.TWechat;
import com.myself.supermarket.mapper.TWechatMapper;
import com.myself.supermarket.service.TWechatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzkemays
 * @since 2020-07-02
 */
@Service
public class TWechatServiceImpl extends ServiceImpl<TWechatMapper, TWechat> implements TWechatService {
@Autowired
private TWechatMapper wechatMapper;
    @Override
    public void adminSend(TWechat wechat) {
        if(wechat.getPower() == 0){
            wechatMapper.insert(wechat);
        }

    }

    @Override
    public void userSend(TWechat wechat) {
        if(wechat.getPower() == 1){
            wechatMapper.insert(wechat);
        }
    }

    @Override
    public List<TWechat> getChat(Long id) {
        QueryWrapper<TWechat> wrapper = new QueryWrapper<>();
        wrapper .orderByAsc("create_time")
                .eq("user_id",id);
        return wechatMapper.selectList(wrapper);
    }
}
