package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author kinoz
 * @Date 2023/3/1 19:47
 * @apiNote
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ArticleListVo {
    //id
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名称
    private Long categoryName;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    //创建时间
    private Date createTime;

}
