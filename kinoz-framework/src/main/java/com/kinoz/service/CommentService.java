package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Hao
* @description 针对表【sg_comment(评论表)】的数据库操作Service
* @createDate 2023-03-20 16:37:12
*/
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
