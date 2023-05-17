package com.kinoz.controller.sys;

import com.kinoz.annotation.SystemLog;
import com.kinoz.domain.ResponseResult;
import com.kinoz.domain.pojo.Role;
import com.kinoz.domain.vo.PageVo;
import com.kinoz.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haogu
 * @date 2023/5/17 20:32
 * @DESCRIPTION
 */
@RestController
@RequestMapping("system/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/listAllRole")
    @SystemLog(note = "列表分页查询")
    public ResponseResult listAllRole(){
        List<Role> roles = roleService.selectRoleAll();
        return ResponseResult.okResult(roles);
    }

    @GetMapping("/list")
    @PreAuthorize("@ps.hasPermission('system:role:list')")
    @SystemLog(note = "角色列表")
    public ResponseResult<PageVo> selectRolePage(Role role, Integer pageNum, Integer pageSize){
        return roleService.selectRolePage(role,pageNum,pageSize);
    }
}
