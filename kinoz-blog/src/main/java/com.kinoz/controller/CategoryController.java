package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kinoz
 * @Date 2023/2/28 19:01
 * @apiNote
 */
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
