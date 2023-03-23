package com.kinoz.filter;

import com.alibaba.fastjson.JSON;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.LoginUser;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.utils.JwtUtil;
import com.kinoz.utils.RedisCache;
import com.kinoz.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
        *获取token
        *Q.友联增加权限后,访问友联,token没有获取成功
        *F在postman测试时 token是放在header里面的而不是params里面
        *Q.退出接口获取不到token,postman测试没问题是因为我们手动在请求头中添加了header信息
        *F.在浏览器中清除前端页面的cache
         */
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //没有token直接放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //token超时,token非法
            //响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        //从redis中获取用户信息
        String userId = claims.getSubject();
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:"+userId);
        //从redis中也有可能获取到null
        if (Objects.isNull(loginUser)){
            //说明登录过期  提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        //存入securityContestHolder,获取权限信息的封装到authentication中
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
