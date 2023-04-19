package com.kinoz.service;

import com.kinoz.domain.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Hao
* @description 用户权限信息相关
* @createDate 2023-04-04 21:57:50
*/
public interface MenuService extends IService<Menu> {

    List<String> selectPermissionsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
