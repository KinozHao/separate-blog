package com.kinoz.controller;

import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.dto.AddCommentDto;
import com.kinoz.domain.pojo.Comment;
import com.kinoz.service.CommentService;
import com.kinoz.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/20
 **/
@RestController
@RequestMapping("/comment")
@Api(tags = "评论相关内容",description = "博客的评论相关接口都在此处")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemConstant.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
        //使用时把DTO转换为POJO进行使用
        Comment comment = BeanCopyUtils.copyBean(addCommentDto,Comment.class);
        return  commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize){
        return  commentService.commentList(SystemConstant.LINK_COMMENT,null,pageNum,pageSize);
    }
}
