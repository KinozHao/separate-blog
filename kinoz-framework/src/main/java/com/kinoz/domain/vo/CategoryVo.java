package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kinoz
 * @Date 2023/2/28 20:14
 * @apiNote
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CategoryVo {
    private Long id;
    private String name;
    //描述
    private String description;
}
