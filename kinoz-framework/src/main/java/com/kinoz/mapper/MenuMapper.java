package com.kinoz.mapper;

import com.kinoz.domain.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Hao
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2023-04-04 21:57:50
* @Entity com.kinoz.domain.pojo.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    //管理员情况下获取所有菜单
    List<Menu> selectAllRouterMenu();

    //非管理员获取对应权限的菜单
    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}




