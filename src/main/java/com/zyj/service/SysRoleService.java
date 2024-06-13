package com.zyj.service;

import com.zyj.dto.Role.SysRole;
import com.zyj.dto.Role.SysRoleDto;
import com.zyj.dto.Role.SysRoleQuery;

import java.util.List;

public interface SysRoleService {
    public SysRole selectRoleById(Integer roleId) throws Exception;

    public List<SysRole> getRole(SysRoleQuery sysRoleQuery, Integer level) throws Exception;

    public int addRole(SysRole sysRole, Integer level) throws Exception;

    public int delRole(SysRole sysRole) throws Exception;

    public int editRole(SysRole sysRole) throws Exception;

    public int getRoleCount(SysRoleQuery sysRoleQuery, Integer level) throws Exception;

    public List<SysRole> getLevelForApply(SysRole sysRole) throws Exception;

    public SysRole getRoleByRoleName(String roleName);

    int updateRoleMenu(SysRoleDto sysRoleDto);
}
