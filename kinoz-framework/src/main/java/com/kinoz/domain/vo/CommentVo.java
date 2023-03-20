package com.kinoz.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/3/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Long id;

    private Long articleId;

    private Long rootId;

    private String content;

    //所回复的目标评论的userid
    private Long toCommentUserId;
    private String toCommentUserName;

    //回复目标评论id
    private Long toCommentId;
    private String  username;

    private Long createBy;

    private Date createTime;

    private List<CommentVo> children;

}
