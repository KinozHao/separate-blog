package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author haogu
* @description 针对表【sg_category(分类表)】的数据库操作Service
* @createDate 2023-02-28 18:51:04
*/
public interface CategoryService extends IService<Category> {

    /**
     * 展示分类列表,用户点击分类看到对应分类下文章
     * 条件
     * 只展示已发布的文章,必须是正常状态的分类
     * @return
     */
    ResponseResult getCategoryList();
}
