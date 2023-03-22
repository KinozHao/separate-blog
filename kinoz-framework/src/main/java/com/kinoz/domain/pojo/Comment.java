package com.kinoz.domain.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName sg_comment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_comment")
public class Comment implements Serializable {
    @TableId
    private Long id;

    private String type;

    private Long articleId;

    private Long rootId;

    private String content;

    private Long toCommentUserId;

    private Long toCommentId;

    /**
     * in this way we use this class to realize autoincrement so we need the annotation TableFiled
     * @see com.kinoz.handler.mybatisplus.MyMetaObjectHandler
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //0(not del) 1(del)
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}