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
public class LinkDto {

    private Long id;

    private String name;

    private String logo;

    private String description;

    private String address;

    private String status;

    private static final long serialVersionUID = 1L;
}
