package com.zyj.service;

import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Menu.SysMenuQuery;
import com.zyj.dto.Role.SysRole;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> getSysMenus(SysMenu sysMenu, Integer roleId) throws Exception;

    public List<SysMenu> getSysMenuList(SysMenu sysMenu, SysRole sysRole) throws Exception;

    public int addMenu(SysMenu sysMenu,Integer roleId) throws Exception;

    public int editMenu(SysMenu sysMenu) throws Exception;

    public int deleteMenu(SysMenu sysMenu) throws Exception;

    public int getMenuCountForOrderCheck(SysMenu sysMenu) throws Exception;


}
