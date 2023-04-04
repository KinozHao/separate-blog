package com.kinoz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签备注
     */
    private String remark;

    private static final long serialVersionUID = 132346542L;
}
