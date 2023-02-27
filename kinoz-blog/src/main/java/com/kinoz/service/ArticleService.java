package com.kinoz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.entity.Article;

/**
 * @author kinoz
 * @Date 2023/2/27 9:43
 * @apiNote
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
