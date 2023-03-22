package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.UserService;
import com.mysql.fabric.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/22
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
}
