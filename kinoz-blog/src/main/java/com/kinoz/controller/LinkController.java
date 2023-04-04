package com.kinoz.controller;

import com.kinoz.domain.ResponseResult;
import com.kinoz.service.LinkService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kinoz
 * @Date 2023/3/7 16:09
 * @apiNote
 */
@RestController
@RequestMapping("/link")
@CrossOrigin
public class LinkController {
    @Resource
    LinkService linkService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLink(){

        return linkService.getAllLink();
    }
}
