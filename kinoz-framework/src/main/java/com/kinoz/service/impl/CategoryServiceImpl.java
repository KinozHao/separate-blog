package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.entity.Article;
import com.kinoz.domain.entity.SgCategory;
import com.kinoz.domain.vo.CategoryVo;
import com.kinoz.service.ArticleService;
import com.kinoz.service.SgCategoryService;
import com.kinoz.mapper.SgCategoryMapper;
import com.kinoz.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author haogu
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2023-02-28 18:51:04
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<SgCategoryMapper, SgCategory>
    implements SgCategoryService{
    @Autowired
    ArticleService articleService;  //查询文章表时需要使用articleService

    @Override
    public ResponseResult getCategoryList() {
        //1.查询文章表 且条件为已经发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstant.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);

        //2.获取文章的分类id，并且去重
        //2.1先把文章列表转化为stream流
        /*Stream<Article> stream = articleList.stream();
        //2.2转换为long类型的分类id 使用map 1-1转换
        Stream<Long> categoryId = stream.map(new Function<Article, Long>() {
            @Override
            public Long apply(Article article) {
                return article.getCategoryId();
            }
        });
        //2.3最终获取所有的categoryId通过集合存放
        Set<Long> categoryIds = categoryId.collect(Collectors.toSet());*/
        //2.4简化模式 Stream and MethodRef
        Set<Long> categoryIds = articleList.stream().
                map(Article::getCategoryId).
                collect(Collectors.toSet());

        //3.查询分类表
        //没有判断状态的数据
        List<SgCategory> sgCategories = listByIds(categoryIds);

        //判断状态的数据 0为正常 1为异常
        List<SgCategory> normalCategories = sgCategories.stream().filter(sgCategory ->
                sgCategory.getStatus().equals(SystemConstant.CATEGORY_STATUS_NORMAL)).collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(sgCategories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}




