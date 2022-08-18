package com.example.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test")
public class TestController {
    @ApiOperation("测试接口")
    @GetMapping("/get")
    public String test(){
        return "hello word";
    }


}
