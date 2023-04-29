package com.kinoz.domain.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 友链
 * @TableName sg_link
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@TableName("sg_link")
public class Link implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String logo;

    /**
     * 
     */
    private String description;

    /**
     * 网站地址
     */
    private String address;

    /**
     * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    private String status;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;

    private static final long serialVersionUID = 1L;
}