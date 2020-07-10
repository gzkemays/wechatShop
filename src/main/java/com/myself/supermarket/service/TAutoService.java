package com.myself.supermarket.service;

import com.myself.supermarket.pojo.TAuto;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface TAutoService extends IService<TAuto> {
    String updateFiles(MultipartFile[] files);
    List<TAuto> getAllAuto();
}
