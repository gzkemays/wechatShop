package com.myself.supermarket.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TProduct;
import com.myself.supermarket.service.TProductService;
import com.myself.supermarket.service.TTypeService;
import com.myself.supermarket.utils.GetAllMessageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Controller
public class TProductController {
    @Autowired
    private TTypeService typeService;
    @Autowired
    private TProductService productService;
    public static final String PRODUCT = "product";
    public static final String PRODUCT_ADD = "addProduct";
    public static final String PRODUCT_REDIRECT = "redirect:/product";
    public static final String PRODUCT_MODAL_BLOCK = "product :: detailsModal";

    @GetMapping("/product")
    public String toProductPage(Page<TProduct> page,Model model){
        page.setSize(5);
        GetAllMessageCount.getAllCountModel(model);
        model.addAttribute("product",productService.getProductPage(page));
        return PRODUCT;
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model){

        model.addAttribute("type",typeService.getAllType());
        return PRODUCT_ADD;
    }
    @PostMapping("/addProduct")
    public String add(MultipartFile file, TProduct product){
        productService.addProduct(file,product);
        return PRODUCT_REDIRECT;
    }

    @GetMapping("/details/{id}")
    public String getDetails(Model model, TProduct product){
        model.addAttribute("details",productService.getById(product));
        return PRODUCT_MODAL_BLOCK;
    }


}

