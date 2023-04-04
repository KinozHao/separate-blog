package com.kinoz.service.impl.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.domain.pojo.Role;
import com.kinoz.service.RoleService;
import com.kinoz.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Hao
* @description 查询用户账户的角色是什么
* @createDate 2023-04-05 14:14:33
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Override
    public List<String> selectRoleByUserId(Long id) {
        //判断是不是管理员 如果是返回集合中只需包括admin
        if (id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户对应的身份信息
        return getBaseMapper().selectRoleByUserId(id);
    }
}




