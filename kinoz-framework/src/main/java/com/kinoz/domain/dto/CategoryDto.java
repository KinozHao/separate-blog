package com.kinoz.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String name;

    private String description;
    /**
     * 状态0:正常,1禁用
     */
    private String status;


}
