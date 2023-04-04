package com.kinoz.service;

import com.kinoz.domain.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Hao
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-04-04 21:57:50
*/
public interface MenuService extends IService<Menu> {

    List<String> selectParamByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
