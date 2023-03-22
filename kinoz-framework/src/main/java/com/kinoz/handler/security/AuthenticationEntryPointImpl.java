package com.kinoz.handler.security;

import com.alibaba.fastjson.JSON;
import com.kinoz.domain.ResponseResult;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Hao Kinoz
 * @Description 认证失败处理器
 * @Date 2023/3/19
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();

        //登录出错
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);

        //响应给前端
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}
