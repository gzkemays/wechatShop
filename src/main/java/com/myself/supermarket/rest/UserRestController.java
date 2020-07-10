package com.myself.supermarket.rest;


import com.myself.supermarket.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired
    private TUserService userService;
    @GetMapping("/restId")
    public Long returnUserId(String openid){
        Long id = userService.returnUserId(openid);
        return id;
    }
}
