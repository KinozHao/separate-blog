package com.kinoz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.pojo.Menu;
import com.kinoz.service.MenuService;
import com.kinoz.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author Hao
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-04-04 21:57:50
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




