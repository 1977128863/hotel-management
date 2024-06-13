package com.zyj.dao;


import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.Role.SysRole;
import com.zyj.dto.Role.SysRoleDto;
import com.zyj.dto.Role.SysRoleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> getRole(@Param("sysRoleQuery") SysRoleQuery sysRoleQuery, @Param("level") Integer level);

    int getRoleCount(@Param("sysRoleQuery") SysRoleQuery sysRoleQuery, @Param("level") Integer level);

    Integer getRoleIdByName(String name);

    List<SysRole> getLevelForApply(SysRole sysRole);

    SysRole getRoleByRoleName(String roleName);

    int deleteByRoleId(Integer roleId);

    int insertBatchRoleMenu(@Param("menuIds") List list,@Param("roleId") Integer roleId);
}
