package com.kinoz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.AddArticleDto;
import com.kinoz.domain.dto.ArticleDto;
import com.kinoz.domain.pojo.Article;
import com.kinoz.domain.vo.ArticleVo;
import com.kinoz.domain.vo.PageVo;

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

    //更新redis中对应id的文章浏览量
    ResponseResult updateViewCount(Long id);


    //-----------admin相关-------------

    //展示文章数据
    ResponseResult<PageVo> showArticleList(Integer pageNum, Integer pageSize, ArticleDto articleDto);

    ResponseResult add(AddArticleDto article);

    void delArticle(Long id);

    void updateArticle(ArticleDto articleDto);

    ArticleVo getAdminArticle(Long id);
}
