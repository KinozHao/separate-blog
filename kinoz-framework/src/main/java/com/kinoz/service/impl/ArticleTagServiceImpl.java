package com.kinoz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.pojo.ArticleTag;
import com.kinoz.mapper.ArticleTagMapper;
import com.kinoz.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
* @author haogu
* @description 针对表【sg_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2023-04-22 19:26:58
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService {

}




