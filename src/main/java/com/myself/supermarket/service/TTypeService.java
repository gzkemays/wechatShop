package com.myself.supermarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TParentType;
import com.myself.supermarket.pojo.TType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myself.supermarket.vo.SearchTypeVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
public interface TTypeService extends IService<TType> {
    boolean addType(TType type, MultipartFile files);
    boolean uploadType(TType tType);
    List<TType> getAllType();
    List<TType> getChildrenAndParentType();
    Page<TType> getChildrenTypePage(Page<TType> page);
    Page<TType> searchChildrenTypePage(Page<TType>page, SearchTypeVO vo);

}
