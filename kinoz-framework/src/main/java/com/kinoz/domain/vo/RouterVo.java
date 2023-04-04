package com.kinoz.domain.vo;

import com.kinoz.domain.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Hao Kinoz
 * @Description
 * @Date 2023/4/17
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouterVo {

    private List<Menu> menus;
}
