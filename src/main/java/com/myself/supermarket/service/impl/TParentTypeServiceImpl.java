package com.myself.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TParentType;
import com.myself.supermarket.mapper.TParentTypeMapper;
import com.myself.supermarket.pojo.TType;
import com.myself.supermarket.service.TParentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.supermarket.vo.SearchTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Service
public class TParentTypeServiceImpl extends ServiceImpl<TParentTypeMapper, TParentType> implements TParentTypeService {
@Autowired
private TParentTypeMapper ptMapper;
    @Override
    public Page<TParentType> getParentTypePage(Page<TParentType> page) {
        return ptMapper.selectPage(page,null);
    }

    @Override
    public List<TParentType> getAllParentType() {
        return ptMapper.selectList(null);
    }

    @Override
    public Page<TParentType> searchParentTypePage(Page<TParentType> page, SearchTypeVO vo) {
        return ptMapper.searchPTypeByContent(page,vo);
    }

    @Override
    public boolean addPType(TParentType type) {
        if(rechecking(type)){
            ptMapper.insert(type);
            return true;
        }
        return false;
    }

    @Override
    public boolean uploadType(TParentType type) {
        if(rechecking(type)){
            ptMapper.updateById(type);
            return true;
        }
        return false;
    }

    // 查询是否有相同
    private boolean rechecking(TParentType type){
        QueryWrapper<TParentType> wrapper = new QueryWrapper<>();
        wrapper.eq("name",type.getName());
        if(ptMapper.selectList(wrapper).size()>0){
            return false;
        }
        return true;
    }
}
