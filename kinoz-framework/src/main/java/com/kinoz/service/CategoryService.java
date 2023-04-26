package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.CategoryDto;
import com.kinoz.domain.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.vo.CategoryVo;
import com.kinoz.domain.vo.PageVo;

import java.util.List;

/**
* @author haogu
* @description 针对表【sg_category(分类表)】的数据库操作Service
* @createDate 2023-02-28 18:51:04
*/
public interface CategoryService extends IService<Category> {

    //分类列表
    ResponseResult getCategoryList();

    ResponseResult<PageVo> showCategoryList(Integer pageNum, Integer pageSize, CategoryDto categoryDto);

    List<CategoryVo> listAllCategory();

    void addCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    void delCategory(List<Long> id);

    Category getCategory(Long id);
}
