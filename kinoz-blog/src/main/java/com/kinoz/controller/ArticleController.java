package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kinoz
 * @Date 2023/2/27 9:45
 * @apiNote
 */
@RestController
@RequestMapping("/article")
//@CrossOrigin    //解决与前端的跨域问题
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*@GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }*/

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result = articleService.hotArticleList();
        return  result;
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        ResponseResult result = articleService.articleList(pageNum,pageSize,categoryId);
        return result;
    }
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        ResponseResult result = articleService.getArticleDetail(id);
        return result;
    }


    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {

        return articleService.updateViewCount(id);
    }
}
