package com.myself.supermarket.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TParentType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myself.supermarket.vo.SearchTypeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
public interface TParentTypeMapper extends BaseMapper<TParentType> {
    TParentType selectTypeWithChildrenType(Long id);
    @Select("select * from t_parent_type where name like concat ('%',#{type.name},'%')")
    Page<TParentType> searchPTypeByContent(Page<TParentType>page,@Param("type") SearchTypeVO vo);
}
