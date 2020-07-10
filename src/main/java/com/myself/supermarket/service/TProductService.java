package com.myself.supermarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TOrder;
import com.myself.supermarket.pojo.TProduct;
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
public interface TProductService extends IService<TProduct> {
    boolean addProduct(MultipartFile file,TProduct product);
    Page<TProduct> getProductPage(Page<TProduct>page);
    List<TProduct> getProductByOrder(TOrder order);
}
