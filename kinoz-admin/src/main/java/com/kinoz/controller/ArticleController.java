package com.kinoz.controller;

import com.kinoz.annotation.SystemLog;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.AddArticleDto;
import com.kinoz.domain.dto.ArticleDto;
import com.kinoz.domain.dto.CategoryDto;
import com.kinoz.domain.dto.LinkDto;
import com.kinoz.domain.pojo.Article;
import com.kinoz.domain.vo.ArticleVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.ArticleService;
import com.kinoz.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @SystemLog(note = "展示文章")
    @GetMapping("/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, ArticleDto articleDto){
        return articleService.showArticleList(pageNum,pageSize,articleDto);
    }

    @PostMapping
    public ResponseResult addArticle(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("{id}")
    public ResponseResult getArticle(@PathVariable(value = "id") Long id){
        ArticleVo adminArticle = articleService.getAdminArticle(id);
        return ResponseResult.okResult(adminArticle);
    }
    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleDto article){
        articleService.updateArticle(article);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult delArticle(@PathVariable Long id){
        articleService.delArticle(id);
        return ResponseResult.okResult();
    }
}
