package com.kinoz.service.impl.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Comment;
import com.kinoz.domain.vo.CommentVo;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.enums.AppHttpCodeEnum;
import com.kinoz.exception.SystemException;
import com.kinoz.mapper.CommentMapper;
import com.kinoz.service.CommentService;
import com.kinoz.service.UserService;
import com.kinoz.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author Hao
* @description 针对表【sg_comment(评论表)】的数据库操作Service实现
* @createDate 2023-03-20 16:37:12
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {
    @Autowired
    UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        var wrapper = new LambdaQueryWrapper<Comment>();

        //对articleId进行判断
        wrapper.eq(SystemConstant.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);

        //根评论 rootid = -1
        wrapper.eq(Comment::getRootId, SystemConstant.COMMENT_ROOT_ID);

        //评论类型
        wrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page = new Page(pageNum, pageSize);
        page(page,wrapper);

        //vo封装
        List<CommentVo> commonVo = toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论的集合,并且赋值给对应的属性
        commonVo.forEach(commentVo -> {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);

        });

        return ResponseResult.okResult(new PageVo(commonVo,page.getTotal()));
    }

    /**
     * 查询所有根评论对应的子评论的集合
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        var wrapper = new LambdaQueryWrapper<Comment>();
        wrapper.eq(Comment::getRootId,id);
        //根据评论的创建时间进行排序
        wrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(wrapper);
        return toCommentVoList(comments);
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);

        //遍历vo集合
        commentVos.stream().forEach(commentVo -> {
            //通过createBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);

            //通过toCommentUserId查询用户的昵称并赋值
            //ToCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId() != -1) {
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        });
        return commentVos;
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        save(comment);
        //评论不能为空
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        return ResponseResult.okResult();
    }
}




