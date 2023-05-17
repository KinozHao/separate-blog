package com.kinoz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinoz.constant.SystemConstant;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Role;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.RoleService;
import com.kinoz.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author Hao
* @description 查询用户角色
* @createDate 2023-04-05 14:14:33
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<String> selectRoleInfoByUserId(Long id) {
        //判断是不是管理员 如果是返回集合中只需包括admin
        if (id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户对应的身份信息
        return getBaseMapper().selectRoleByUserId(id);
    }


    @Override
    public List<Role> selectRoleAll() {
        return list(Wrappers.<Role>lambdaQuery().eq(Role::getStatus, SystemConstant.NORMAL));
    }

    @Override
    public ResponseResult<PageVo> selectRolePage(Role role, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.hasText(role.getRoleName()),Role::getRoleName,role.getRoleName());
        wrapper.eq(StringUtils.hasText(role.getStatus()),Role::getStatus,role.getStatus());
        wrapper.orderByAsc(Role::getRoleSort);

        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        //转换成VO
        List<Role> roles = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(roles);
        return ResponseResult.okResult(pageVo);
    }
}




