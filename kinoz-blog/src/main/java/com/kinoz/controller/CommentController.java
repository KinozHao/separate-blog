package com.kinoz.controller;

import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Comment;
import com.kinoz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/20
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstant.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return  commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return  commentService.commentList(SystemConstant.LINK_COMMENT,null,pageNum,pageSize);
    }
}
