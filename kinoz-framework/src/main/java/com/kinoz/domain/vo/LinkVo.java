package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author kinoz
 * @Date 2023/3/7 16:27
 * @apiNote
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LinkVo {

    private Long id;

    private String name;


    private String logo;


    private String description;

    /**
     * 网站地址
     */
    private String address;

}
