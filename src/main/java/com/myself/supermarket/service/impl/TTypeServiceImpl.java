package com.myself.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TType;
import com.myself.supermarket.mapper.TTypeMapper;
import com.myself.supermarket.service.TTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.supermarket.vo.SearchTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Service
public class TTypeServiceImpl extends ServiceImpl<TTypeMapper, TType> implements TTypeService {
@Autowired
private TTypeMapper typeMapper;

    @Transactional
    @Override
    public boolean addType(TType type, MultipartFile files) {
        if(rechecking(type)){
            picConverterURL(type,files);
            typeMapper.insert(type);
            return true;
        }
        return false;
    }
    @Transactional
    @Override
    public boolean uploadType(TType type) {
        if(rechecking(type)){
            typeMapper.updateById(type);
            return true;
        }
        return false;
    }

    @Override
    public List<TType> getAllType() {
        return typeMapper.selectList(null);
    }

    @Override
    public List<TType> getChildrenAndParentType() {
        return typeMapper.selectPTypeByCTypeID();
    }

    @Override
    public Page<TType> getChildrenTypePage(Page<TType> page) {
        return typeMapper.selectPTypeByCTypeID(page);
    }

    @Override
    public Page<TType> searchChildrenTypePage(Page<TType> page, SearchTypeVO vo) {
        return typeMapper.searchPTypeByCTypeID(page,vo);
    }

    private boolean rechecking(TType type){
        QueryWrapper<TType> wrapper = new QueryWrapper<>();
        wrapper.eq("name",type.getName());
        if(typeMapper.selectList(wrapper).size()>0){
            return false;
        }
        return true;
    }

    private void picConverterURL(TType type , MultipartFile file){
        String fileName = file.getOriginalFilename();
        int prefixIndex = fileName.lastIndexOf(".");
        String prefix = fileName.substring(prefixIndex);
        fileName = type.getName()+ UUID.randomUUID() + "_" +prefix;
        String dirPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/typePic/";
        File f = new File(dirPath+fileName);
        if(!f.exists()){
            f.mkdirs();
        }
        try {
            file.transferTo(f);
            type.setUrl("http://yz3fsk.natappfree.cc/supermarket/typePic/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
