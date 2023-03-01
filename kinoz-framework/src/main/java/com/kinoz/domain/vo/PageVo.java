package com.kinoz.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author kinoz
 * @Date 2023/3/1 19:52
 * @apiNote 分页使用
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PageVo {
    private List row;
    private Long total;
}
