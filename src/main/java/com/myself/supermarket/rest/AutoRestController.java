package com.myself.supermarket.rest;

import com.myself.supermarket.pojo.TAuto;
import com.myself.supermarket.service.TAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoRestController {
    @Autowired
    private TAutoService autoService;
    @GetMapping("/restAuto")
    public List<TAuto> getAutoPic(){
        return autoService.getAllAuto();
    }
}
