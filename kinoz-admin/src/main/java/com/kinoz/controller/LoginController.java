package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.domain.pojo.Menu;
import com.kinoz.domain.pojo.User;
import com.kinoz.domain.vo.AdminUserInfoVo;
import com.kinoz.domain.vo.RouterVo;
import com.kinoz.domain.vo.UserInfoVo;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.exception.SystemException;
import com.kinoz.service.LoginService;
import com.kinoz.service.MenuService;
import com.kinoz.service.RoleService;
import com.kinoz.utils.BeanCopyUtils;
import com.kinoz.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }


    /**
     * 查询登录用户的具体信息
     * @return
     */
    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限
        List<String> perms = menuService.selectParamByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roles = roleService.selectRoleByUserId(loginUser.getUser().getId());
        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        //最终封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roles,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    /**
     * 动态路由
     * @return
     */
    @GetMapping("getRouters")
    public ResponseResult<RouterVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RouterVo(menus));
    }
}
