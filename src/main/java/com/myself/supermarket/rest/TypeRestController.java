package com.myself.supermarket.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TParentType;
import com.myself.supermarket.pojo.TType;
import com.myself.supermarket.service.TParentTypeService;
import com.myself.supermarket.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TypeRestController {
    @Autowired
    private TTypeService typeService;
    @Autowired
    private TParentTypeService ptService;
    @GetMapping("/restPType")
    public List<TParentType> getPType(){
        return ptService.getAllParentType();
    }
    @GetMapping("/restCType")
    public List<TType> getCType(){
        return typeService.getAllType();
    }
}
