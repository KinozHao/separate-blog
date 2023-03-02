package com.kinoz.domain.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author kinoz
 * @Date 2023/2/27 14:35
 * @apiNote
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article")
@Accessors(chain = true)
public class Article {
    @TableId
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章类型:1 文章 2草稿
    //private String type;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    //代表此字段在数据表中不存在
    @TableField(exist = false)
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //评论数
    //private Integer commentCount;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;

    private Long createBy;

    //todo 注解转换存在问题,前端并没有转换成功
    //等同于FastJsonConfig
    //jackson包下
    //@JsonFormat(timezone = "GMT+8",pattern = "yyyy/MM/dd HH:mm:ss")
    //Alibaba包下
    //@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
}
