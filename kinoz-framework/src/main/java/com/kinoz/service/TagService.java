package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.vo.PageVo;

import java.util.List;

/**
* @author Hao
* @description 针对表【sg_tag(标签)】的数据库操作Service
* @createDate 2023-04-02 18:49:37
*/
public interface TagService extends IService<Tag> {

    //展示标签
    ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagDto tagListDto);

    //添加标签
    void addTag(TagDto tagDto);

    //删除单个标签
    void delTag(Long tagId);

    Tag getTag(Long id);

    void updateTag(TagDto tagDto);



}
