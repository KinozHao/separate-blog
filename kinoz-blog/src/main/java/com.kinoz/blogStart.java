package com.kinoz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author kinoz
 * @Date 2023/2/27 9:25
 * @apiNote
 */
@SpringBootApplication
@MapperScan("com.kinoz.mapper")
public class blogStart {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(blogStart.class, args);
    }
}
