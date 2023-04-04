package com.kinoz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kinoz.domain.pojo.ArticleTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/18
 **/
@Repository
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 批量保存文章标签
     *
     * @param articleId 文章id
     * @param tagIdList 标签id列表
     */
    void saveBatchArticleTag(@Param("articleId") Integer articleId, List<Integer> tagIdList);
}
