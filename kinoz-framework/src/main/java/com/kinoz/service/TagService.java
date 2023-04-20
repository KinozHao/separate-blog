package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.domain.vo.TagVo;

import java.util.List;

/**
* @author Hao
* @description 标签页面CRUD
* @createDate 2023-04-02 18:49:37
*/
public interface TagService extends IService<Tag> {
    //展示标签
    ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, TagDto tagListDto);

    //添加标签
    void addTag(TagDto tagDto);

    //删除单个标签
    void delTagById(Long tagId);

    //获取标签
    Tag getTag(Long id);

    //更新标签信息
    void updateTag(TagDto tagDto);

    //写博文 标签
    List<TagVo> listAllTag();

}
