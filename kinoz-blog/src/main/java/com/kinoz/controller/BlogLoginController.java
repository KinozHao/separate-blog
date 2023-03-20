package com.kinoz.controller;

import com.kinoz.exception.SystemException;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LiuMiss
 * @Description
 * @Date 2023/3/17
 **/
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //前端防小白,后端防老6,双重校验
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
