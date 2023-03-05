package com.kinoz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Article;

/**
 * @author kinoz
 * @Date 2023/2/27 9:43
 * @apiNote
 */
public interface ArticleService extends IService<Article> {

    //热门文章
    ResponseResult hotArticleList();

    //分页查询文章列表
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    //文章详情
    ResponseResult getArticleDetail(Long id);
}
