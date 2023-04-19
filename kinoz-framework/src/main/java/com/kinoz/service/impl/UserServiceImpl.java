package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.User;
import com.kinoz.domain.vo.UserInfoVo;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.exception.SystemException;
import com.kinoz.mapper.UserMapper;
import com.kinoz.service.UserService;
import com.kinoz.utils.BeanCopyUtils;
import com.kinoz.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author Hao
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-03-17 14:30:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public ResponseResult userInfo() {
        //获取用户id
        Long userId = SecurityUtils.getUserId();
        //根据id查询用户信息
        User user = getById(userId);
        //封装vo类
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult registerUser(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //可对所有数据进行判断,略...

        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }
    private boolean userNameExist(String userName) {
        var queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName,userName);

        //大于0说明存在，否则则不存在
        return count(queryWrapper)>0;
    }
    private boolean nickNameExist(String nickName) {
        var queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getNickName,nickName);
        return count(queryWrapper)>0;
    }

}




