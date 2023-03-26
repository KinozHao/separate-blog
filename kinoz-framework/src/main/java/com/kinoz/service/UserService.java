package com.kinoz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;

/**
* @author Hao
* @createDate 2023-03-17 14:30:52
*/
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult registerUser(User user);
}
