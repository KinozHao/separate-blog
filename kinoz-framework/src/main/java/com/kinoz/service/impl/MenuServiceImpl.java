package com.kinoz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.pojo.Menu;
import com.kinoz.service.MenuService;
import com.kinoz.mapper.MenuMapper;
import com.kinoz.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Hao
* @description 查询用户账户所具有的权限信息
* @createDate 2023-04-04 21:57:50
*/
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Override
    public List<String> selectPermissionsByUserId(Long id) {
        //如果是管理员具备所有权限 1L即为管理员
        if (SecurityUtils.isAdmin()){
           var wrapper = new LambdaQueryWrapper<Menu>();
           wrapper.in(Menu::getMenuType, SystemConstant.MENU,SystemConstant.BUTTON);
           wrapper.eq(Menu::getStatus,SystemConstant.STATUS_NORMAL);
            List<Menu> list = list(wrapper);
            List<String> perms = list.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则查询用户对应的权限信息
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是不是管理员
        if (SecurityUtils.isAdmin()){
            //如果是 获取符合要求的menu
            menus = menuMapper.selectAllRouterMenu();
        }else {
            //否则 获取当前用户所具有的menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //构建menu_tree的形式
        //条例:首先找出第一层菜单,然后去找他们的子菜单设置到children属性中
        //如果需要继续寻找子子菜单下的菜单，例如三级菜单就需要使用递归的思想方法,参考getChildren方法
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    private List<Menu> builderMenuTree(List<Menu> menus,Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     *
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                //递归策略如果存在三层的情况,会继续寻找子菜单下的子菜单
                .map(m -> m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return  childrenList;
    }
}




