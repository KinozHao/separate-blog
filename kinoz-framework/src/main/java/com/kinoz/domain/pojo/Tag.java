package com.kinoz.domain.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName sg_tag
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sg_tag")
public class Tag implements Serializable {
    @TableId
    private Long id;

    private String name;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;

    private String remark;

    private static final long serialVersionUID = 1L;
}