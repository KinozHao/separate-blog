package com.kinoz.service.impl.admin;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.ArticleTag;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.mapper.ArticleTagMapper;
import com.kinoz.service.TagService;
import com.kinoz.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author Hao
* @description 针对表【sg_tag(标签)】的数据库操作Service实现
* @createDate 2023-04-02 18:49:37
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagDto tagListDto) {
        //分页查询
        var wrapper = new LambdaQueryWrapper<Tag>();
        wrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        wrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());
        var page = new Page<Tag>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public void addTag(TagDto tagDto) {
        // 标签是否存在
        var wrapper = new LambdaQueryWrapper<Tag>();
        wrapper.select(Tag::getId).eq(Tag::getName,tagDto.getName());
        Tag existTag = tagMapper.selectOne(wrapper);
        //如果标签存在给出异常
        Assert.isNull(existTag,tagDto.getName()+"标签已存在");

        // 添加新标签
        Tag newTage = Tag.builder().name(tagDto.getName()).remark(tagDto.getRemark()).build();
        baseMapper.insert(newTage);
    }

    @Override
    public void delTag(List<Integer> tagIdList) {
        //判断标签下是否有文章
        var wrapper = new LambdaQueryWrapper<ArticleTag>();
        wrapper.in(ArticleTag::getArticleId,tagIdList);
        Long count = Long.valueOf(articleTagMapper.selectCount(wrapper));
        //删除提示
        Assert.isFalse(count > 0, "删除失败，标签下存在文章");
        //批量删除标签
        tagMapper.deleteBatchIds(tagIdList);
    }
}




