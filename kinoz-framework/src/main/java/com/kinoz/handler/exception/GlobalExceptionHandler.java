package com.kinoz.handler.exception;

import com.kinoz.exception.SystemException;
import com.kinoz.domain.ResponseResult;
import com.kinoz.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/20
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //控制台输出异常方便调试
        log.error("got sys error info:",e);
        //从异常对象中提取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //控制台输出异常方便调试
        log.error("got sys error info:",e);
        //从异常对象中提取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
