package com.kinoz.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.entity.Article;
import com.kinoz.domain.vo.HotArticleVo;
import com.kinoz.mapper.ArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kinoz
 * @Date 2023/2/27 9:42
 * @apiNote
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService{

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装为ResponseResult返回
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //必须是正文文章
        wrapper.eq(Article::getStatus,0);
        //浏览量进行排序 降序
        wrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<Article>(1,10);
        page(page,wrapper);

        List<Article> articles = page.getRecords();
        List<HotArticleVo> hotArticleVos = new ArrayList<>();

        //Bean拷贝
        for (Article article : articles) {
            //把获取到的字段属性赋值给vo
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            //把这个vo的添加到一个泛型为HotArticleVo的集合中
            hotArticleVos.add(vo);
        }

        //实际返回给前端的数据为vo中的
        return ResponseResult.okResult().ok(hotArticleVos);
    }
}
