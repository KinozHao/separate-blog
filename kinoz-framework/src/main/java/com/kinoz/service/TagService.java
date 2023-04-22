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

    ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, TagDto tagListDto);

    void addTag(TagDto tagDto);

    void delTagById(List<Long> tagId);

    Tag getTag(Long id);

    void updateTag(TagDto tagDto);

    List<TagVo> listAllTag();

}
