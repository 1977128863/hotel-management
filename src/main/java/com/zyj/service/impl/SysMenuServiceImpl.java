package com.zyj.service.impl;

import com.zyj.dao.SysMenuMapper;
import com.zyj.dao.SysRoleMapper;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Menu.SysMenuQuery;
import com.zyj.dto.Role.SysRole;
import com.zyj.service.SysMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    private static Logger logger = LogManager.getLogger(SysMenuServiceImpl.class);
    @Autowired
    private SysMenuMapper mm;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional
    public List<SysMenu> getSysMenus(SysMenu sysMenu, Integer roleId) throws Exception {
        List<SysMenu> list = new ArrayList<SysMenu>();
        try {
            list = mm.getSysMenus(sysMenu, roleId);
        } catch (Exception e) {
            logger.error("菜单信息获取失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public List<SysMenu> getSysMenuList(SysMenu sysMenu, SysRole sysRole) throws Exception {
        List<SysMenu> list = new ArrayList<SysMenu>();
        try {
            list = mm.getChildrenMenuList(sysMenu, sysRole);
        } catch (Exception e) {
            logger.error("子菜单信息获取失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int addMenu(SysMenu sysMenu,Integer roleId) throws Exception {
        int count = 0;
        try {
            SysMenu sysMenu1 = new SysMenu();
            sysMenu1.setTitle(sysMenu.getTitle());
            SysMenu resultMenu = mm.getMenuCount(sysMenu1);
            if (resultMenu != null) {
                count = 2;
            } else {
                count = mm.insertSelective(sysMenu);
                ArrayList<Integer> list = new ArrayList<>();
                list.add(sysMenu.getMenuId());
                sysRoleMapper.insertBatchRoleMenu(list,roleId);
            }

        } catch (Exception e) {
            count = -1;
            logger.error("新增菜单失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int editMenu(SysMenu sysMenu) throws Exception {
        int count = 0;
        try {
            SysMenu sysMenu1 = new SysMenu();
            sysMenu1.setTitle(sysMenu.getTitle());
            SysMenu resultMenu = mm.getMenuCount(sysMenu1);
            if (resultMenu == null || resultMenu.getMenuId().equals(sysMenu.getMenuId())) {
                count = mm.updateByPrimaryKeySelective(sysMenu);
            } else {
                count = 2;
            }


        } catch (Exception e) {
            count = -1;
            logger.error("修改菜单失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int deleteMenu(SysMenu sysMenu) throws Exception {
        int count = 0;
        try {
            if (sysMenu.getMenuId() != null) {
                SysMenu sysMenu1 = new SysMenu();
                sysMenu1.setTopMenuId(sysMenu.getMenuId());
                count = mm.deleteByPrimaryKey(sysMenu1);
                if (count > 0) {
                    count = mm.deleteByPrimaryKey(sysMenu);
                    if (count > 0) {
                        return count;
                    } else {
                        return 0;
                    }
                } else {
                    count = mm.deleteByPrimaryKey(sysMenu);
                }
            } else {
                return count;
            }

        } catch (Exception e) {
            logger.error("删除菜单失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int getMenuCountForOrderCheck(SysMenu sysMenu) throws Exception {
        int count = 0;
        try {
            SysMenu sysMenu1 = new SysMenu();
            sysMenu1.setTopMenuId(sysMenu.getTopMenuId());
            sysMenu1.setOrders(sysMenu.getOrders());
            SysMenu resultMenu = mm.getMenuCount(sysMenu1);
            if (resultMenu == null || resultMenu.getMenuId().equals(sysMenu.getMenuId())) {

            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("菜单顺序判断重复出错", e);
        }


        return count;
    }
}
