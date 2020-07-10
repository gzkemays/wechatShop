package com.myself.supermarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TUser;
import com.myself.supermarket.mapper.TUserMapper;
import com.myself.supermarket.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.supermarket.utils.WechatClientUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {
    public static final String APP_ID = "wx8250166e19a40758";
    public static final String SECRET ="313b5e5fdf1ba6e30de2b972e48f2246";
    public static final String LOGIN_API = "https://api.weixin.qq.com/sns/jscode2session" +
            "?appid=" + APP_ID +
            "&secret=" + SECRET +
            "&js_code=JSCODE" +
            "&grant_type=authorization_code";
    public static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token" +
            "?grant_type=client_credential" +
            "&appid=" + APP_ID +
            "&secret=" + SECRET;
    @Autowired
    private TUserMapper userMapper;

    @Transactional
    @Override
    public Long getUserLoginTag(String openid , String info) {
//        String token = WechatClientUtil.get(LOGIN_API.replaceAll("JSCODE",code));
//        System.out.println("code = " + code);
//        Map map = JSON.parseObject(token);
//        String openid = (String)map.get("openid");
//        System.out.println("openid = " + openid);
//        TUser user = new TUser();

        QueryWrapper<TUser>wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openid);
        if(userMapper.selectOne(wrapper) != null){
            TUser updateUser = userMessageChange(openid, info);
            updateUser.setOpenId(openid);
            userMapper.updateById(updateUser);
        }else{
            TUser insertUser = userMessageChange(openid, info);
            insertUser.setOpenId(openid);
            userMapper.insert(insertUser);
        }
        wrapper.select("id");
       return userMapper.selectOne(wrapper).getId();
    }

    @Override
    public Page<TUser> getUserPage(Page<TUser>page) {
        return userMapper.selectUserMessageAndNUM(page);
    }


    @Override
    public String returnOpenId(String code) {
        String token = WechatClientUtil.get(LOGIN_API.replaceAll("JSCODE",code));
        Map map = JSON.parseObject(token);
        String openid = (String)map.get("openid");
        return openid;
    }

    @Override
    public Long returnUserId(String openid) {
        QueryWrapper<TUser>wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openid);
        return userMapper.selectOne(wrapper).getId();
    }

    public TUser userMessageChange( String openId, String info ) {
      Map map = JSON.parseObject(info);
          QueryWrapper<TUser>wrapper = new QueryWrapper<>();
          wrapper.eq("open_id",openId);
          TUser one = userMapper.selectOne(wrapper);
          TUser user = new TUser();
        if (one != null) {
            BeanUtils.copyProperties(one, user);
        }
        user.setUsername((String)map.get("nickName"));
        user.setGender((Integer) map.get("gender"));
        user.setAddress(map.get("country") +"_"+map.get("province")+"_"+map.get("city"));
        user.setAvatarUrl((String)map.get("avatarUrl"));
        return user;
    }
}
