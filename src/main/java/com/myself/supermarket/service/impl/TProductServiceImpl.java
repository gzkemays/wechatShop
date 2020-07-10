package com.myself.supermarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.mapper.TOrderMapper;
import com.myself.supermarket.mapper.TProductOrdersMapper;
import com.myself.supermarket.pojo.TOrder;
import com.myself.supermarket.pojo.TProduct;
import com.myself.supermarket.mapper.TProductMapper;
import com.myself.supermarket.pojo.TProductOrders;
import com.myself.supermarket.service.TProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TProductServiceImpl extends ServiceImpl<TProductMapper, TProduct> implements TProductService {
@Autowired
private TProductMapper productMapper;
@Autowired
private TOrderMapper orderMapper;
@Autowired
private TProductOrdersMapper productOrdersMapper;
    @Transactional
    @Override
    public boolean addProduct(MultipartFile file, TProduct product) {
        String fileName = file.getOriginalFilename();
        int prefix = fileName.lastIndexOf(".");
        fileName =  UUID.randomUUID() + "_"+ product.getProductName() + fileName.substring(prefix);
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/static/productPic/";
        File f = new File(path + fileName);
        if(!f.exists()){
            f.mkdirs();
        }
        try {
            file.transferTo(f);
            product.setPicture("http://yz3fsk.natappfree.cc/supermarket/productPic/"+fileName);
            productMapper.insert(product);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<TProduct> getProductPage(Page<TProduct> page) {

        return productMapper.selectPage(page,null);
    }

    @Override
    public List<TProduct> getProductByOrder(TOrder order) {
        QueryWrapper<TProduct> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select product_id from t_product_orders where orders_id="+order.getId());
        List<TProduct> products = productMapper.selectList(wrapper);
        for (TProduct product : products){
            product.setPNum(setProductCount(product,order));
        }
        return products;
    }

    private Long setProductCount(TProduct product,TOrder order){
        QueryWrapper<TProductOrders> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id",product.getId()).eq("orders_id",order.getId());
        return Long.valueOf(productOrdersMapper.selectCount(wrapper)).longValue();
    }
}
