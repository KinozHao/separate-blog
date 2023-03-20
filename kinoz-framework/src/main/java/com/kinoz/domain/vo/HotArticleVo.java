package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kinoz
 * @Date 2023/2/27 16:48
 * @apiNote
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotArticleVo {
    private Long id;
    //标题
    private String title;
    //访问量
    private Long viewCount;
}
