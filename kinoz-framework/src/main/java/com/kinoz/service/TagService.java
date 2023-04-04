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

    ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagDto tagListDto);

    void addTag(TagDto tagDto);

    void delTag(List<Integer> tagIdList);
}
