package com.kinoz.controller.details;

import com.kinoz.annotation.SystemLog;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.ArticleDto;
import com.kinoz.domain.dto.CategoryDto;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Category;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.CategoryVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.ArticleService;
import com.kinoz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @SystemLog(note = "分页显示")
    @GetMapping("/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, CategoryDto categoryDto){
        return categoryService.showCategoryList(pageNum,pageSize,categoryDto);
    }

    @GetMapping("/listAllCategory")
    @SystemLog(note = "写博文")
    public ResponseResult<?> listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @PostMapping
    public ResponseResult addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return ResponseResult.okResult();
    }

    @PutMapping
    public ResponseResult updateCategory(@RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(categoryDto);
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getCategory(@PathVariable Long id){
        Category category = categoryService.getCategory(id);
        return ResponseResult.okResult(category);
    }

    @DeleteMapping("{id}")
    public ResponseResult delCategory(@PathVariable("id") List<Long> id){
        categoryService.delCategory(id);
        return ResponseResult.okResult();
    }
}
