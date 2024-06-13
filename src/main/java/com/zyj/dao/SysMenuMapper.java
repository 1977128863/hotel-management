package com.zyj.dao;

import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Menu.SysMenuQuery;
import com.zyj.dto.Role.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(SysMenu sysMenu);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> getSysMenus(@Param("sysMenu") SysMenu sysMenu, @Param("roleId") Integer roleId);

    List<SysMenu> getChildrenMenuList(@Param("sysMenu") SysMenu sysMenu, @Param("sysRole") SysRole sysRole);

    SysMenu getMenuCount(SysMenu sysMenu);

    Integer getMenuCountForOrder(SysMenu sysMenu);


}