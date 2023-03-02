package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author kinoz
 * @Date 2023/3/2 21:22
 * @apiNote
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ArticleDetailVo {
    //所属分类Id
    private Long categoryId;
    //所属分类名称
    private String categoryName;
    //文章内容
    private String content;
    //创建时间
    private Date createTime;
    //id
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;


}
