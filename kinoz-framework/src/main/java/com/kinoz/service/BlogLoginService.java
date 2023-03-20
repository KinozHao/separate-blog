package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;

/**
 * @Author LiuMiss
 * @Description
 * @Date 2023/3/17
 **/
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
