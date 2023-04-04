package com.kinoz.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kinoz.domain.vo.MenuVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @TableName sys_menu
 */
@Data
@TableName("sys_menu")
@NoArgsConstructor
@AllArgsConstructor
//开启链式编程 set具有返回值
@Accessors(chain = true)
public class Menu implements Serializable {
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

    private Long createBy;

    private Date createTime;

    //后台登录会查询此字段要么传vo要么使用注解
    //让mybatis-plus 忽略此字段的查询
    @TableField(exist = false)
    private List<Menu> children;

    private static final long serialVersionUID = 1L;
}