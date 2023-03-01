package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Article;
import com.kinoz.domain.vo.ArticleListVo;
import com.kinoz.domain.vo.HotArticleVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.mapper.ArticleMapper;
import com.kinoz.service.ArticleService;
import com.kinoz.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author kinoz
 * @Date 2023/2/27 9:42
 * @apiNote
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    /**
     * 查询热门文章 封装为ResponseResult返回
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //必须是正文文章,使用常量类来定义草稿和正式文章代号,提高可读性
        wrapper.eq(Article::getStatus, SystemConstant.ARTICLE_STATUS_NORMAL);
        //浏览量进行排序 降序
        wrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<Article>(1,10);
        page(page,wrapper);

        List<Article> articles = page.getRecords();


        //Bean拷贝
        /*for (Article article : articles) {
            List<HotArticleVo> hotArticleVos = new ArrayList<>();
            //把获取到的字段属性赋值给vo
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            //把这个vo的添加到一个泛型为HotArticleVo的集合中
            hotArticleVos.add(vo);
        }*/
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        //实际返回给前端的数据为vo中的
        return ResponseResult.okResult().ok(hotArticleVos);
    }

    /**
     * 分页查询文章列表
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> query = new LambdaQueryWrapper<>();
        //若有categoryId 查询时要和传入相同
        //代码解读:先判断有无categoryId,且不能小于0 满足的话再对前端传过来的categoryId和数据库做校验
        query.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式发布的
        query.eq(Article::getStatus,SystemConstant.ARTICLE_STATUS_NORMAL);

        //对isTop进行降序排序
        query.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,query);

        //分装查询结构
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        //前端需要的数据外层的两个参数row和total
        //row就是我们获取到的articleListVos的所有参数,total通过page.gotTotal获取所有的页数
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
