package com.kinoz.service;

import com.kinoz.domain.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Hao
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-04-05 14:14:33
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleByUserId(Long id);
}
