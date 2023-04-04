package com.kinoz.domain.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mockito.NotExtensible;

/**
 * @TableName sys_role
 */
@Data
@TableName("sys_role")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Long id;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String status;

    private String delFlag;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}