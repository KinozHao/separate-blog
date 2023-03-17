package com.kinoz.service.impl;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.domain.pojo.User;
import com.kinoz.domain.vo.BlogUserLoginVo;
import com.kinoz.domain.vo.UserInfoVo;
import com.kinoz.service.BlogLoginService;
import com.kinoz.utils.BeanCopyUtils;
import com.kinoz.utils.JwtUtil;
import com.kinoz.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author LiuMiss
 * @Description
 * @Date 2023/3/17
 **/
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    RedisCache redisCache;

    /**
     * 实现用户登录校验
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {
        //1.获取登录用户信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //获取用户id且生成token
        var loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //JWT存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //把User转换为userinfoVO
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        var vo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(vo);
    }
}
