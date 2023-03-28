package com.kinoz.runner;

import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.pojo.Article;
import com.kinoz.mapper.ArticleMapper;
import com.kinoz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/28
 **/
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(
                        Collectors.toMap(
                                article -> article.getId().toString(),
                                article -> article.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap(SystemConstant.REDIS_VIEW_KEY,viewCountMap);
    }
}
