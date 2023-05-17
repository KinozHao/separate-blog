package com.kinoz.controller.details;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.LinkDto;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.pojo.Link;
import com.kinoz.domain.pojo.Tag;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    LinkService linkService;

    @GetMapping("list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, LinkDto linkDto){
        return linkService.showLinkList(pageNum,pageSize,linkDto);
    }

    @PostMapping
    public ResponseResult<?> add(@RequestBody LinkDto linkDto){
        linkService.add(linkDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult<?> delete(@PathVariable("id") Long id){
        linkService.deleteById(id);
        return  ResponseResult.okResult();
    }

    @PutMapping
    public ResponseResult<?> update(@RequestBody LinkDto linkDto){
        linkService.updateLink(linkDto);
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getTag(@PathVariable Long id){
        Link link = linkService.getLink(id);
        return ResponseResult.okResult(link);
    }

}
