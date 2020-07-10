package com.myself.supermarket.rest;

import com.myself.supermarket.pojo.TProduct;
import com.myself.supermarket.service.TProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    private TProductService productService;
    @GetMapping("/restProduct")
    public List<TProduct> getAllProduct(){
        return productService.getBaseMapper().selectList( null);
    }
    @GetMapping("/restProduct/{id}")
    public TProduct getOneProduct(@PathVariable("id")Long id){
        return productService.getBaseMapper().selectById(id);
    }
}
