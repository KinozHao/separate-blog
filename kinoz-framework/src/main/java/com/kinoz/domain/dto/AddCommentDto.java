package com.kinoz.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Hao Kinoz
 * @Description DTO(DATA TRANSFER OBJECT)
 * @Date 2023/3/31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(discriminator = "添加评论的DTO")
public class AddCommentDto {

    @ApiModelProperty(notes = "article id")
    private Long id;

    @ApiModelProperty(notes = "0(not del) 1(del)")
    private Integer delFlag;

    private String type;

    private Long articleId;

    private Long rootId;

    private String content;

    private Long toCommentUserId;

    private Long toCommentId;

    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;



    private static final long serialVersionUID = 1L;
}
