package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/2
 **/
public interface LoginService {
    ResponseResult login(User user);
}
