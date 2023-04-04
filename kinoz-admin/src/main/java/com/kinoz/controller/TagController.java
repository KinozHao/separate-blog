package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 显示后台标签
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/content/tag/list")
    public ResponseResult<PageVo> tagList(Integer pageNum, Integer pageSize, TagDto tagDto){
        return tagService.list(pageNum,pageSize,tagDto);
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

    @DeleteMapping("/content/tag/{id}")
    public ResponseResult<?> delTag(@PathVariable("id") List<Integer> tagIdList){
        tagService.delTag(tagIdList);
        return ResponseResult.okResult();
    }


}
