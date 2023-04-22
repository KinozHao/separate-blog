package com.kinoz.controller;

import com.kinoz.annotation.SystemLog;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.domain.vo.TagVo;
import com.kinoz.service.TagService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, TagDto tagDto){
        return tagService.showTagList(pageNum,pageSize,tagDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagDto tagDto){
        tagService.addTag(tagDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delTag(@PathVariable("id") List<Long> id){
        tagService.delTagById(id);
        return ResponseResult.okResult();
    }

    @PutMapping
    @SystemLog(note = "更新标签")
    public ResponseResult updateTag(@RequestBody TagDto tagDto){
        tagService.updateTag(tagDto);
        return ResponseResult.okResult();
    }


    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable Long id){
        Tag tag = tagService.getTag(id);
        return ResponseResult.okResult(tag);
    }


    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
