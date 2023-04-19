package com.kinoz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/2
 **/
@SpringBootApplication
@MapperScan("com.kinoz.mapper")
public class adminStarter {
    public static void main(String[] args) {
        SpringApplication.run(adminStarter.class,args);
    }
}
