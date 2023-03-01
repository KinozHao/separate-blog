package com.kinoz.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * @author kinoz
 * @Date 2023/3/1 20:09
 * @apiNote
 */
@Configuration
public class MybatisPlusConfig {

    //Mybatis分页插件 拦截器 有这个时候mybatisPlus分页才会生效
    @Bean
    public MybatisPlusInterceptor pageMethod(){
        MybatisPlusInterceptor tool = new MybatisPlusInterceptor();
        PaginationInnerInterceptor pii = new PaginationInnerInterceptor();
        //请求页面大于最大页面后操作 true调回首页 false继续请求
        pii.setOverflow(true);
        //最大页显示设置
        pii.setMaxLimit(500L);
        tool.addInnerInterceptor(pii);
        return tool;
    }
}
