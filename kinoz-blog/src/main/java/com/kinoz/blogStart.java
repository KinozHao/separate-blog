package com.kinoz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kinoz
 * @Date 2023/2/27 9:25
 * @apiNote
 */
@SpringBootApplication
@MapperScan("com.kinoz.mapper")
@EnableSwagger2
//开启定时任务功能
@EnableScheduling
public class blogStart {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(blogStart.class, args);
    }
}
