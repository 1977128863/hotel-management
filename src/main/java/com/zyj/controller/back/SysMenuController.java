package com.zyj.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.zyj.common.DataJson;
import com.zyj.common.WebRedisJson;
import com.zyj.common.ZtreeDto;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Role.SysRole;
import com.zyj.service.SysMenuService;
import com.zyj.util.RedisUtil;
import com.zyj.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/back/menu")
public class SysMenuController {
    private static Logger logger = LogManager.getLogger(SysMenuController.class);

    @Autowired
    private SysMenuService ms;

    @RequestMapping("/getMenu")
    @ResponseBody
    public JSONObject getMenu(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String adminName = Util.getCookie(request, "adminName");
        WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
        SysRole sysRole = webRedisJson.getSysRole();
        List<SysMenu> menuList = webRedisJson.getSysMenu();

        try {
            for (SysMenu sysMenu : menuList
            ) {
                SysMenu childrenMenu = new SysMenu();
                childrenMenu.setMenuId(sysMenu.getMenuId());
                List<SysMenu> childrenList = ms.getSysMenuList(childrenMenu, sysRole);
                sysMenu.setChildren(childrenList);
            }
        } catch (Exception e) {
            logger.error("菜单获取失败", e);
        }

        Iterator<SysMenu> iterator = menuList.iterator();
        while (iterator.hasNext()) {
            SysMenu sysMenu = iterator.next();
            if (sysMenu.getChildren().size() == 0) {
                iterator.remove();
            }
        }

        jsonObject.put("flag", true);
        jsonObject.put("message", "权限菜单获取成功");
        jsonObject.put("contentManagement", menuList);
        return jsonObject;
    }

    @PostMapping("/sysRoleMapper")
    @ResponseBody
    public DataJson getMenuByTree(SysMenu sysMenu, HttpServletRequest request) {
        String adminName = Util.getCookie(request, "adminName");
        WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
        SysRole sysRole = webRedisJson.getSysRole();
        Integer roleId = sysRole.getRoleId();
        List<ZtreeDto> treeList = new ArrayList<ZtreeDto>();

        try {
            List<SysMenu> sysMenusList = ms.getSysMenus(sysMenu, roleId);
            for (int i = 0; i < sysMenusList.size(); i++) {
                ZtreeDto ztree = new ZtreeDto();
                ztree.setId(sysMenusList.get(i).getMenuId());
                ztree.setName(sysMenusList.get(i).getTitle());
                ztree.setpId(sysMenusList.get(i).getTopMenuId());
                ztree.setOpen("0");
                treeList.add(ztree);
            }
        } catch (Exception e) {
            logger.error("菜单获取失败", e);
        }

        return new DataJson(200, "获取成功", treeList, treeList.size());

    }

    @PostMapping("/getMenuForEcho")
    @ResponseBody
    public DataJson getMenuForEcho(SysMenu sysMenu, HttpServletRequest request) {
        String adminName = Util.getCookie(request, "adminName");
        WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
        SysRole sysRole = webRedisJson.getSysRole();

        List<SysMenu> echoMenu = new ArrayList<>();

        try {
            echoMenu = ms.getSysMenus(sysMenu, sysRole.getRoleId());
            if (echoMenu == null) {
                return new DataJson(300, "获取失败");
            }
        } catch (Exception e) {
            logger.error("回显菜单查询失败", e);
        }


        return new DataJson(200, "获取成功", echoMenu, echoMenu.size());
    }

    @PostMapping("/addMenu")
    @ResponseBody
    public DataJson addMenu(SysMenu sysMenu, HttpServletRequest request) {
        int count = 0;
        String adminName = Util.getCookie(request, "adminName");
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreatePer(adminName);
        sysMenu.setIsDelete(0);
        sysMenu.setSpread("false");

        WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
        SysRole sysRole = webRedisJson.getSysRole();
        Integer roleId = sysRole.getRoleId();

        try {
            count = ms.addMenu(sysMenu,roleId);
            if (count == 2) {
                return new DataJson(300, "菜单已存在，请重试");
            } else if (count == -1) {
                return new DataJson(500, "网络错误");
            }
        } catch (Exception e) {
            logger.error("菜单新增失败", e);
        }
        return new DataJson(200, "新增成功");
    }

    @PostMapping("/orderCheck")
    @ResponseBody
    public DataJson orderCheck(SysMenu sysMenu) {
        int count = 0;
        try {
            count = ms.getMenuCountForOrderCheck(sysMenu);
            if (count == 2) {
                return new DataJson(300, "该顺序已存在，请重试");
            }
        } catch (Exception e) {
            logger.error("顺序获取出错", e);
        }

        return new DataJson(200, "可以新增");
    }

    @PostMapping("/editMenu")
    @ResponseBody
    public DataJson editMenu(SysMenu sysMenu) {
        int count = 0;

        try {
            count = ms.editMenu(sysMenu);
            if (count == -1) {
                return new DataJson(500, "网络错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count == 2) {
                return new DataJson(300, "改菜单已存在，请重试");
            }
        } catch (Exception e) {
            logger.error("修改菜单", e);
        }

        return new DataJson(200, "修改成功");
    }

    @PostMapping("/delMenu")
    @ResponseBody
    public DataJson delMenu(SysMenu sysMenu) {
        int count = 0;
        try {
            count = ms.deleteMenu(sysMenu);
            if (count < 0) {
                return new DataJson(500, "系统错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "删除失败");
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return new DataJson(200, "删除成功");
    }
}
