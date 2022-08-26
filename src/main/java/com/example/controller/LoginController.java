package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login (){
        System.out.println("执行登录方法");
        return "redirect:main.html";
    }
    //成功跳转页面
    //PreAuthorize的表达式允许ROLE_开头,也可以不ROLE_开头.Secured配置类不允许ROLE_开头
    //@Secured("ROLE_zhangSan")
    @PreAuthorize("hasRole('zhangSan')")
    @RequestMapping("/toMain")
    public String toMain (){
        System.out.println("执行登录方法");
        return "redirect:main.html";
    }
    //失败跳转页面
    @RequestMapping("/toError")
    public String toError (){
        System.out.println("执行登录方法");
        return "redirect:error.html";
    }
}

