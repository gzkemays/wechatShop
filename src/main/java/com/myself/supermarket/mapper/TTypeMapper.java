package com.myself.supermarket.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myself.supermarket.vo.SearchTypeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Repository
public interface TTypeMapper extends BaseMapper<TType> {
    Page<TType> selectPTypeByCTypeID(Page<TType> page);
    List<TType> selectPTypeByCTypeID();
    Page<TType> searchPTypeByCTypeID(Page<TType> page,@Param("type") SearchTypeVO vo);

}
