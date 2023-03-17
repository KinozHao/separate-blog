package com.kinoz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.pojo.User;
import com.kinoz.mapper.UserMapper;
import com.kinoz.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author Hao
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-03-17 14:30:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




