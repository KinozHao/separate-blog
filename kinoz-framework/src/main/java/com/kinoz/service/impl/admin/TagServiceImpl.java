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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


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
    public void delTag(Long tagId) {
        //判断标签下是否有文章
        /*var wrapper = new LambdaQueryWrapper<ArticleTag>();
        wrapper.in(ArticleTag::getArticleId,tagId);
        Long count = Long.valueOf(articleTagMapper.selectCount(wrapper));
        //删除提示
        Assert.isFalse(count > 0, "删除失败，标签下存在文章");*/
        //删除标签
        baseMapper.deleteById(tagId);
        //TODO 批量删除标签
    }

    @Override
    public Tag getTag(Long id) {
        Tag tag = baseMapper.selectById(id);
        if (tag == null) {
            throw new RuntimeException("查询的标签不存在");
        }
        return tag;
    }

    @Override
    @Transactional
    public void updateTag(TagDto tagDto) {
        // 标签是否存在
        var wrapper = new LambdaQueryWrapper<Tag>();
        wrapper.select(Tag::getId).eq(Tag::getName,tagDto.getName());
        Tag existTag = tagMapper.selectOne(wrapper);
        //如果标签存在给出异常
        Assert.isNull(existTag,tagDto.getName()+"标签已存在");

        // 修改标签
        Tag newTage = Tag
                .builder()
                .id(tagDto.getId())
                .name(tagDto.getName())
                .remark(tagDto.getRemark()).build();
        baseMapper.updateById(newTage);

    }

}




