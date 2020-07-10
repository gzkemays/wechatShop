package com.myself.supermarket.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myself.supermarket.pojo.TParentType;
import com.myself.supermarket.pojo.TType;
import com.myself.supermarket.service.TParentTypeService;
import com.myself.supermarket.service.TTypeService;
import com.myself.supermarket.utils.GetAllMessageCount;
import com.myself.supermarket.vo.SearchTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzkemays
 * @since 2020-06-28
 */
@Controller
public class TTypeController {
    @Autowired
    private TParentTypeService ptypeService;
    @Autowired
    private TTypeService typeService;

    public static final String TYPE = "type";
    public static final String TYPE_REDIRECT = "redirect:/type/";
    public static final String TYPE_FRAGMENT_BLOCK = "type::typeList";

    @GetMapping("/type/{level}/{sTag}")
    public String toTypePage(Model model, Page<TParentType> tPage,Page<TType> cPage
            ,@PathVariable("level") int level
            ,@PathVariable("sTag") int sTag
            ,HttpSession session){
        GetAllMessageCount.getAllCountModel(model);
        mustValueModelAndProperties(model,level,tPage,cPage);
        if(sTag==0){
            defaultLevelAllPage(model, tPage, cPage, level);
        }else{
            SearchTypeVO vo;
            vo = (SearchTypeVO) session.getAttribute("vo");
            searchPage(model,tPage,cPage,vo,session);
        }
        return TYPE;
    }

    @PostMapping("/upload")
    public String uploadType(@Param(value = "level") int level, TParentType pType, TType cType, RedirectAttributes attributes){
        if(level == 0){
            redirect_rechecking(ptypeService.uploadType(pType),attributes);
        }else{
            redirect_rechecking(typeService.uploadType(cType),attributes);
        }
        return TYPE_REDIRECT+level+"/0";
    }

    @PostMapping("/add")
    public String addType(@Param(value = "isParent") boolean isParent
            , TParentType pType
            , TType cType, @Param(value = "files")MultipartFile files){
        int level ;
        if(isParent){
            ptypeService.addPType(pType);
            level = 0;
        }else{
            typeService.addType(cType,files);
            level = 1;
        }
        return TYPE_REDIRECT+level+"/0";
    }

    @PostMapping("/search")
    public String searchType(Model model, Page<TParentType> tPage, Page<TType> cPage
            , SearchTypeVO vo, HttpSession session){
        mustValueModelAndProperties(model,vo.getLevel(),tPage,cPage);
        searchPage(model,tPage,cPage,vo,session);
        return TYPE_FRAGMENT_BLOCK;
    }

    private void redirect_rechecking(boolean judge,RedirectAttributes attributes){
        if(judge){
            attributes.addFlashAttribute("uploadTag","ok");
        }else{
            attributes.addFlashAttribute("uploadTag","fail");
        }
    }
    private void mustValueModelAndProperties(Model model,int level,Page<TParentType> tPage,Page<TType> cPage){
        tPage.setSize(5);
        cPage.setSize(5);
        model.addAttribute("pType",ptypeService.getAllParentType());
        model.addAttribute("level",level);
    }
    private void searchPage(Model model,Page<TParentType> tPage
            , Page<TType> cPage
            , SearchTypeVO vo
            , HttpSession session){
        if (vo.getLevel() == 0){
            model.addAttribute("type",ptypeService.searchParentTypePage(tPage,vo));
        }else{
            model.addAttribute("type",typeService.searchChildrenTypePage(cPage,vo));
        }
        model.addAttribute("sTag",1);
        session.setAttribute("vo",vo);
    }
    private void defaultLevelAllPage(Model model, Page<TParentType> tPage,Page<TType> cPage,int level){
        int sTag = 0;
        if(level == 0){
            model.addAttribute("type", ptypeService.getParentTypePage(tPage));
        }else{
            model.addAttribute("type",typeService.getChildrenTypePage(cPage));
        }
        model.addAttribute("sTag",sTag);
    }

}

