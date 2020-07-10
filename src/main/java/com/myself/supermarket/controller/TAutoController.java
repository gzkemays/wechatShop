package com.myself.supermarket.controller;


import com.myself.supermarket.service.TAutoService;
import com.myself.supermarket.utils.GetAllMessageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
public class TAutoController {
    @Autowired
    private TAutoService autoService;
    public static final String PLAY = "play";
    @GetMapping("/play")
    public String toAutoPage(Model model){
        GetAllMessageCount.getAllCountModel(model);
        return PLAY;
    }

    @PostMapping("/play")
    public String uploadPic(MultipartFile[] files, Model model){
        autoService.updateFiles(files);
        return PLAY;
    }
}

