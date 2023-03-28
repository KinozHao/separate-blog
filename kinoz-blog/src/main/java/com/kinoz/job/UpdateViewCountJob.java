package com.kinoz.job;

import com.kinoz.domain.pojo.Article;
import com.kinoz.service.ArticleService;
import com.kinoz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Hao Kinoz
 * @Description 每隔2分钟把redis数据更新到mysql
 * @Date 2023/3/28
 **/
@Component
public class UpdateViewCountJob {
    @Autowired
    RedisCache redisCache;
    @Resource
    ArticleService articleService;

    //cron的内容表示每隔2分钟执行一次
    @Scheduled(cron = "0 0/2 * * * ?")
    public void jobTest(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        //把redis的Map数据转换为单列数据
        List<Article> articleCount = viewCountMap
                .entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //更新到数据库中
        articleService.updateBatchById(articleCount);
    }
}
