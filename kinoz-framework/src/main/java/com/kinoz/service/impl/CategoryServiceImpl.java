package com.kinoz.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.CategoryDto;
import com.kinoz.domain.pojo.Article;
import com.kinoz.domain.pojo.Category;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.CategoryVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.ArticleService;
import com.kinoz.service.CategoryService;
import com.kinoz.mapper.CategoryMapper;
import com.kinoz.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author haogu
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2023-02-28 18:51:04
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {
    @Autowired
    ArticleService articleService;  //查询文章表时需要使用articleService
    @Autowired
    CategoryMapper categoryMapper;

    /**
     *  页面上需要展示分类列表，用户可以点击具体的分类查看该分类下的文章列表。
     *  注意： ①要求只展示有发布正式文章的分类 ②必须是正常状态的分类
     * @return
     */
    @Override
    public ResponseResult getCategoryList() {
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
        List<Category> sgCategories = listByIds(categoryIds);

        //判断状态的数据 0为正常 1为异常
        List<Category> normalCategories = sgCategories.stream()
                .filter(var -> var.getStatus().equals(SystemConstant.CATEGORY_STATUS_NORMAL))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(sgCategories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }


    //---------------------------------后台相关接口------------------------------------
    @Override
    public ResponseResult<PageVo> showCategoryList(Integer pageNum, Integer pageSize, CategoryDto categoryDto) {
        //分页查询
        var wrapper = new LambdaQueryWrapper<Category>();
        wrapper.eq(StringUtils.hasText(categoryDto.getName()),Category::getName,categoryDto.getName());
        wrapper.eq(StringUtils.hasText(categoryDto.getStatus()),Category::getStatus,categoryDto.getStatus());
        var page = new Page<Category>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstant.CATEGORY_STATUS_NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        // 标签是否存在
        var wrapper = new LambdaQueryWrapper<Category>();
        wrapper.select(Category::getId).eq(Category::getName,categoryDto.getName());
        Category existTag = categoryMapper.selectOne(wrapper);
        //如果标签存在给出异常
        Assert.isNull(existTag,categoryDto.getName()+"标签已存在");

        // 添加新标签
        Category newCategory = Category.builder().name(categoryDto.getName()).description(categoryDto.getDescription()).build();
        baseMapper.insert(newCategory);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        var wrapper = new LambdaQueryWrapper<Category>();
        wrapper.select(Category::getId).eq(Category::getName,categoryDto.getName());
        categoryMapper.selectOne(wrapper);
        //Assert.isNull(existCategory,categoryDto.getName()+"分类已存在");

        Category newCategory = Category.builder().id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .status(categoryDto.getStatus()).build();
        baseMapper.updateById(newCategory);
    }

    @Override
    public void delCategory(List<Long> catId) {
        for (Long id : catId){
            removeById(id);
        }
    }

    @Override
    public Category getCategory(Long id) {
        Category category = baseMapper.selectById(id);
        if (category == null){
            throw new RuntimeException("查询的分类不存在");
        }
        return category;
    }
}




