package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.domain.pojo.User;
import com.kinoz.domain.vo.AdminUserInfoVo;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.exception.SystemException;
import com.kinoz.service.LoginService;
import com.kinoz.service.MenuService;
import com.kinoz.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/2
 **/
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    MenuService menuService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    // TODO
    @GetMapping("getinfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询 权限 角色信息 最终封装数据返回

        return null;
    }
}
