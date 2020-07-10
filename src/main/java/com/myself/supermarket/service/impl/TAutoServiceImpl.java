package com.myself.supermarket.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.myself.supermarket.pojo.TAuto;
import com.myself.supermarket.mapper.TAutoMapper;
import com.myself.supermarket.service.TAutoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
public class TAutoServiceImpl extends ServiceImpl<TAutoMapper, TAuto> implements TAutoService {
@Autowired
private TAutoMapper autoMapper;
    @Override
    public String updateFiles(MultipartFile[] files) {
        Long i = 1L;
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            int prefix = fileName.lastIndexOf(".");
            // 获取后缀
            String lastPrefix = fileName.substring(prefix);
            // 重命名
            fileName = i+lastPrefix;
            String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/static/autoPlay/";
            String path = filePath + fileName;
            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            try {
                file.transferTo(f);
                TAuto auto = new TAuto();
                auto.setUrl("http://yz3fsk.natappfree.cc/supermarket/autoPlay/"+fileName);
                if(autoMapper.selectList(null).size()>=3){
                    auto.setId(i);
                    autoMapper.updateById(auto);
                }else{
                    autoMapper.insert(auto);
                }
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "fail";
    }

    @Override
    public List<TAuto> getAllAuto() {
        return autoMapper.selectList(null);
    }
}
