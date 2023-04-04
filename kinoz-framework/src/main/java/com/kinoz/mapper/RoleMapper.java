package com.kinoz.mapper;

import com.kinoz.domain.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Hao
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2023-04-05 14:14:33
* @Entity com.kinoz.domain.pojo.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleByUserId(Long userId);

}




