package com.kinoz.service;

import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinoz.domain.vo.PageVo;

import java.util.List;

/**
* @author Hao
* @description 用户权限信息相关
* @createDate 2023-04-05 14:14:33
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleInfoByUserId(Long id);

    List<Role> selectRoleAll();

    ResponseResult<PageVo> selectRolePage(Role role, Integer pageNum, Integer pageSize);
}
