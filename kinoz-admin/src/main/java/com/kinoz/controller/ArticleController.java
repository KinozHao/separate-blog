package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.ArticleDto;
import com.kinoz.domain.dto.LinkDto;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.ArticleService;
import com.kinoz.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 展示文章
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/content/article/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, ArticleDto articleDto){
        return articleService.showArticleList(pageNum,pageSize,articleDto);
    }
}
