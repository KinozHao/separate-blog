package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/17
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuVo {

    private Long id;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private Integer isFrame;

    private String menuType;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private Date createTime;

    private List<MenuVo> children;

    private String remark;

    private String delFlag;

    private static final long serialVersionUID = 1L;

}
