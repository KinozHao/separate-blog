package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.ArticleDto;
import com.kinoz.domain.dto.CategoryDto;
import com.kinoz.domain.pojo.Category;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.ArticleService;
import com.kinoz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 展示文章
     * @param pageNum
     * @param pageSize
     * @param
     * @return*/

    @GetMapping("/content/category/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, CategoryDto categoryDto){
        return categoryService.showCategoryList(pageNum,pageSize,categoryDto);
    }
}
