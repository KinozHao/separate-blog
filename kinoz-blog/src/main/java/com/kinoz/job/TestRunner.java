package com.kinoz.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Hao Kinoz
 * @Description 项目启动时预处理
 * @Date 2023/3/28
 **/
@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化");
    }
}
