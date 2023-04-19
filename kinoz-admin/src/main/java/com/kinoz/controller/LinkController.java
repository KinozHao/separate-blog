package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.LinkDto;
import com.kinoz.domain.dto.TagDto;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@RestController
public class LinkController {
    @Autowired
    LinkService linkService;

    /**
     * 展示友联
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/content/link/list")
    public ResponseResult<PageVo> showTagList(Integer pageNum, Integer pageSize, LinkDto linkDto){
        return linkService.showLinkList(pageNum,pageSize,linkDto);
    }
}
