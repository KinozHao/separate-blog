package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {

    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String name;
}
