package com.zyj.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.zyj.common.DataJson;
import com.zyj.common.MyAnnotation;
import com.zyj.common.WebRedisJson;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Role.SysRole;
import com.zyj.dto.Role.SysRoleDto;
import com.zyj.dto.Role.SysRoleQuery;
import com.zyj.service.SysAdminService;
import com.zyj.service.SysMenuService;
import com.zyj.service.SysRoleService;
import com.zyj.util.RedisUtil;
import com.zyj.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/role")
public class SysRoleController {
    private static final Logger logger = LogManager.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService rs;


    @PostMapping("/getRole")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public DataJson getRole(SysRoleQuery sysRoleQuery, Integer page, HttpServletRequest request) {
        List<SysRole> list = new ArrayList<SysRole>();
        int count = 0;
        try {
            if (page != null) {
                int start = sysRoleQuery.getLimit() * (page - 1);
                sysRoleQuery.setStart(start);
            }
            String adminName = Util.getCookie(request, "adminName");
            WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
            Integer level = webRedisJson.getSysRole().getLevel();
            list = rs.getRole(sysRoleQuery, level);
            count = rs.getRoleCount(sysRoleQuery, level);

        } catch (Exception e) {
            logger.error("角色或者数量获取失败", e);
        }
        if (count < 0) {
            return new DataJson(-1, "获取失败");
        }

        return new DataJson(0, "获取成功", list, count);


    }

    @PostMapping("/addRole")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public DataJson addRole(SysRole sysRole, HttpServletRequest request) {
        try {
            String adminName = Util.getCookie(request, "adminName");
            sysRole.setCreatePer(adminName);
            WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
            Integer level = webRedisJson.getSysRole().getLevel();
            sysRole.setCreateTime(new Date());
            int count = rs.addRole(sysRole, level);
            if (count == 2) {
                return new DataJson(300, "该角色已存在！");
            } else if (count == -1) {
                return new DataJson(500, "服务器异常，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "新增失败！");
            }
        } catch (Exception e) {
            logger.error("角色新增失败", e);
        }
        return new DataJson(200, "新增成功");
    }

    @PostMapping("/delRole")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public DataJson delRole(SysRole sysRole) {
        int count = 0;
        try {
            count = rs.delRole(sysRole);
            if (count < 0) {
                return new DataJson(500, "服务器异常，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "删除失败");
            }
        } catch (Exception e) {
            logger.error("角色删除失败", e);
        }
        return new DataJson(200, "删除成功");
    }

    @PostMapping("/editRole")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public DataJson editRole(SysRole sysRole, HttpServletRequest request) {

        int count = 0;
        sysRole.setModifyPer(Util.getCookie(request, "adminName"));
        sysRole.setModifyTime(new Date());
        try {
            count = rs.editRole(sysRole);
            if (count < 0) {
                return new DataJson(500, "服务器出错，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count == 2) {
                return new DataJson(300, "该角色已存在，请重试！");
            }
        } catch (Exception e) {
            logger.error("修改角色信息失败", e);
        }

        return new DataJson(200, "修改成功");
    }

    @PostMapping("/editRole/updateRoleMenu")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public DataJson updateRoleMenu(SysRoleDto sysRoleDto) {
        int count = rs.updateRoleMenu(sysRoleDto);
        if (count >= 0){
            return new DataJson(200, "修改成功");
        }else {
            return new DataJson(500, "修改失败");
        }
    }



    @PostMapping("/getLevelForApply")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public JSONObject getLevelForApply(SysRole sysRole) {
        List<SysRole> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {
//            if (sysRole.getRoleId() == null){
//
//            }
            list = rs.getLevelForApply(sysRole);
            if (list == null || list.size() == 0) {
                jsonObject.put("code", 300);
                jsonObject.put("message", "角色值获取失败");
                return jsonObject;
            }
        } catch (Exception e) {
            logger.error("角色值获取失败", e);
        }

        jsonObject.put("code", 200);
        jsonObject.put("message", "角色值获取成功");
        jsonObject.put("data", list);
        return jsonObject;
    }

    @PostMapping("/getHaveMenu")
    @MyAnnotation({"roleManage"})
    @ResponseBody
    public DataJson<SysMenu> getAllMenu(HttpServletRequest request) throws Exception {
        String adminName = Util.getCookie(request, "adminName");
        WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
        List<SysMenu> list = webRedisJson.getSysMenu();

        return new DataJson<>(200, "获取成功", list, list.size());
    }


    @GetMapping("/getvalue")
    public void getvalue() {
        System.out.println(RedisUtil.get("admin"));
    }

}
