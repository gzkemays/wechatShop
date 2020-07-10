package com.myself.supermarket.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Repository
public interface TUserMapper extends BaseMapper<TUser> {
    Page<TUser> selectUserMessageAndNUM(Page<TUser>page);
}
