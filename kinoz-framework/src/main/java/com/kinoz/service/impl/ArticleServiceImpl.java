package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.ArticleDto;
import com.kinoz.domain.pojo.Article;
import com.kinoz.domain.pojo.Category;
import com.kinoz.domain.pojo.Link;
import com.kinoz.domain.vo.ArticleDetailVo;
import com.kinoz.domain.vo.ArticleListVo;
import com.kinoz.domain.vo.HotArticleVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.mapper.ArticleMapper;
import com.kinoz.service.ArticleService;
import com.kinoz.service.CategoryService;
import com.kinoz.utils.BeanCopyUtils;
import com.kinoz.utils.RedisCache;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kinoz
 * @Date 2023/2/27 9:42
 * @apiNote
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
    @Resource
    CategoryService categoryService;
    @Resource
    RedisCache redisCache;

    /**
     *  需要查询浏览量最高的前10篇文章的信息。要求展示文章标题和浏览量。把能让用户自己点击跳转到具体的文章详情进行浏览。
     *  注意：不能把草稿展示出来，不能把删除了的文章查询出来。要按照浏览量进行降序排序。
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
     * 在首页和分类页面都需要查询文章列表。
     * 首页：查询所有的文章
     * 分类页面：查询对应分类下的文章
     * 要求：①只能查询正式发布的文章 ②置顶的文章要显示在最前面
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


        //查询categoryName
        //方式1 使用foreach
        /*List<Article> articles = page.getRecords();
        for (Article article : articles) {
            //articleId去查询articleName进行设置
            //1.根据categoryId查询categoryName
            Category category = categoryService.getById(article.getCategoryId());
            //2.再把categoryName赋给文章
            article.setCategoryName(category.getName());
        }*/

        //方式2 使用stream流 匿名内部类 方便理解
        /*List<Article> articles = page.getRecords();
        articles.stream()
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {
                        //获取分类id 查询分类信息 获取分类名称
                        Long id = article.getCategoryId();
                        Category category = categoryService.getById(id);
                        String categoryName = category.getName();
                        article.setCategoryName(categoryName);
                        return article;
                    }
                });*/

        //方式3 使用stream流 λ 减少冗余
        List<Article> articles = page.getRecords();
        articles = articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

        //封装查询结构
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);


        //前端需要的数据外层的两个参数row和total
        //row就是我们获取到的articleListVos的所有参数,total通过page.gotTotal获取所有的页数
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 要求在文章列表点击阅读全文时能够跳转到文章详情页面，可以让用户阅读文章正文。
     * 要求：①要在文章详情中展示其分类名
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取实时浏览量
        Integer viewCount = redisCache.getCacheMapValue(SystemConstant.REDIS_VIEW_KEY, id.toString());
        article.setViewCount(viewCount.longValue());
        //转换vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        //获取分类id
        Long categoryId = articleDetailVo.getCategoryId();
        //根据分类id获取分类(分类是在category表查的,所有使用了自动注入的category接口)
        Category category = categoryService.getById(categoryId);
        //使用optional进行封装,避免空指针
        Optional<Category> cate = Optional.ofNullable(category);
        cate.ifPresent(value -> articleDetailVo.setCategoryName(value.getName()));
        /*if (category != null){
            articleDetailVo.setCategoryName(category.getName());
        }*/

        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应id的浏览量
        redisCache.incrementCacheMapValue(SystemConstant.REDIS_VIEW_KEY,id.toString(),1);
        return null;
    }

    @Override
    public ResponseResult<PageVo> showArticleList(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
        //分页查询
        var wrapper = new LambdaQueryWrapper<Article>();
        wrapper.eq(StringUtils.hasText(articleDto.getTitle()),Article::getTitle,articleDto.getTitle());
        wrapper.eq(StringUtils.hasText(articleDto.getSummary()),Article::getSummary,articleDto.getSummary());
        var page = new Page<Article>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }


}
