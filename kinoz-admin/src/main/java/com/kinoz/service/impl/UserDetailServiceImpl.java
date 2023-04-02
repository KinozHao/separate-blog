package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.domain.pojo.User;
import com.kinoz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/2
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        var query = new LambdaQueryWrapper<User>();
        query.eq(User::getUserName,username);
        User user = userMapper.selectOne(query);
        //判断是否查询到用户,如果没有查询到抛出异常
        if (Objects.isNull(user))
        {
            throw  new RuntimeException("查询此用户不存在!");
        }

        //返回用户信息
        // TODO 查询权限信息封装
        return new LoginUser(user);
    }
}
