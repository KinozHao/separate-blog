package com.kinoz.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Hao Kinoz
 * @Description 定时器测试
 * @Date 2023/3/28
 **/
@Component
public class TestJob {

    //cron的内容表示每隔五秒钟执行一次
    /*@Scheduled(cron = "0/5 * * * * ?")
    public void jobTest(){
        System.out.println("定时器测试");
    }*/
}
