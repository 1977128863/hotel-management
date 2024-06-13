package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.common.WebRedisJson;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.PowerHtml.SysPowerHtml;
import com.zyj.dto.PowerHtml.SysPowerHtmlQuery;
import com.zyj.dto.Role.SysRole;
import com.zyj.service.SysAdminService;
import com.zyj.service.SysMenuService;
import com.zyj.service.SysPowerHtmlService;
import com.zyj.service.SysRoleService;
import com.zyj.util.RedisUtil;
import com.zyj.util.Util;
import com.zyj.util.getPowerHtml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/back/html")
public class sysPowerHtmlController {
    private static Logger logger = LogManager.getLogger(sysPowerHtmlController.class);

    @Autowired
    private SysPowerHtmlService ps;


    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Value("${filePath}")
    private String path;

    /**
     * 同步html
     *
     * @return
     */
    @PostMapping("/syncPowerHtml")
    @ResponseBody
    public DataJson syncPowerHtml() {
        int count = 0;

        try {
            List<String> htmlPathList = getPowerHtml.gethtml(path);
            count = ps.insertHtml(htmlPathList);
            if (count == -1) {
                return new DataJson(500, "系统错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "同步失败或无新增数据");
            }
        } catch (Exception e) {
            logger.error("html同步失败", e);
        }

        return new DataJson(200, "html同步成功");
    }


    @PostMapping("/getPowerHtml")
    @ResponseBody
    public DataJson getPowerHtml(SysPowerHtmlQuery sysPowerHtmlQuery) {
        int start = sysPowerHtmlQuery.getLimit() * (sysPowerHtmlQuery.getPage() - 1);
        sysPowerHtmlQuery.setStart(start);
        List<SysPowerHtml> list = new ArrayList<SysPowerHtml>();
        int count = 0;
        try {
            list = ps.selectHtml(sysPowerHtmlQuery);
            count = ps.selectHtmlCount(sysPowerHtmlQuery);
            if (list.size() == 0 && count == 0) {
                return new DataJson(0, "暂无数据");
            } else if (count == 0 || list == null) {
                return new DataJson(-1, "获取失败");
            } else if (count < 0) {
                return new DataJson(-1, "系统错误，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("获取html失败", e);
        }
        return new DataJson(0, "获取成功", list, count);
    }

    @PostMapping("/editPowerHtml")
    @ResponseBody
    public DataJson editPowerHtml(String htmlHref, Integer menuId) {
        int count = 0;
        try {
            count = ps.updateHtmlAndMenu(htmlHref, menuId);
            if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count < 0) {
                return new DataJson(500, "系统错误请联系管理员");
            }
        } catch (Exception e) {
            logger.error("修改html关联菜单失败", e);
        }

        return new DataJson(200, "修改成功");
    }

    @PostMapping("/getManagementByRoleId")
    @ResponseBody
    public DataJson getManagementByRoleId(String roleName) throws Exception {
        SysRole roleByRoleName = sysRoleService.getRoleByRoleName(roleName);

        List<SysMenu> sysMenus = sysMenuService.getSysMenus(new SysMenu(), roleByRoleName.getRoleId());

        return new DataJson(200, "获取成功", sysMenus, sysMenus.size());
    }

}
