package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.TagService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* 标签管理
* */
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 展示标签
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/content/tag/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, TagDto tagDto){
        return tagService.showTagList(pageNum,pageSize,tagDto);
    }

    /**
     * 添加标签
     * @param tagDto
     * @return
     */
    @PostMapping("/content/tag")
    public ResponseResult<?> addTag(@RequestBody TagDto tagDto){
        tagService.addTag(tagDto);
        return ResponseResult.okResult();
    }

    /**
     * 删除标签
     * @param
     * @return
     */
    @DeleteMapping("/content/tag/{id}")
    public ResponseResult<?> delTag(@PathVariable("id") Long id){
        tagService.delTagById(id);
        return ResponseResult.okResult();
    }

    /**
     * 查询标签
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/content/tag/{id}")
    public ResponseResult<?> getTag(@PathVariable Long id){
        Tag tag = tagService.getTag(id);
        return ResponseResult.okResult(tag);
    }


    /**
     * 修改标签
     * @param tagDto
     * @return
     */
    @PutMapping("/content/tag")
    public ResponseResult<?> updateTag(@Validated @RequestBody TagDto tagDto){
        tagService.updateTag(tagDto);
        return ResponseResult.okResult();
    }



}
