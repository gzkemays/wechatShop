package com.myself.supermarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TParentType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myself.supermarket.pojo.TType;
import com.myself.supermarket.vo.SearchTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
public interface TParentTypeService extends IService<TParentType> {
    boolean addPType(TParentType type);
    boolean uploadType(TParentType tParentType);
    Page<TParentType> getParentTypePage(Page<TParentType> page);
    List<TParentType> getAllParentType();
    Page<TParentType> searchParentTypePage(Page<TParentType> page, @Param("type") SearchTypeVO vo);
}
