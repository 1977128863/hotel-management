package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.common.MyAnnotation;
import com.zyj.common.WebRedisJson;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Menu.SysMenuQuery;
import com.zyj.dto.Role.SysRole;
import com.zyj.service.SysAdminService;
import com.zyj.service.SysMenuService;
import com.zyj.service.SysPowerHtmlService;
import com.zyj.service.SysRoleService;
import com.zyj.util.MD5Util;
import com.zyj.util.RedisUtil;
import com.zyj.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/admin")
public class SysAdminController {
    private static final Logger logger = LogManager.getLogger(SysAdminController.class);

    @Autowired
    private SysAdminService as;
    @Autowired
    private SysRoleService rs;
    @Autowired
    private SysMenuService ms;
    @Autowired
    private SysPowerHtmlService phs;

    @GetMapping("/toLogin")
    public String toLogin() {
        return "back/login_manage/login";
    }

    @PostMapping("/getAdmin")
    @ResponseBody
    public DataJson getAdmin(SysAdminQuery sysAdminQuery, Integer page, HttpServletRequest request) {
        List<SysAdmin> list = new ArrayList<SysAdmin>();
        int count = 0;
        try {
            if (page != null) {
                int start = sysAdminQuery.getLimit() * (page - 1);
                sysAdminQuery.setStart(start);
            }

            String adminName = Util.getCookie(request, "adminName");
            WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
            Integer level = null;
            if (webRedisJson != null) {
                level = webRedisJson.getSysRole().getLevel();
            }
            sysAdminQuery.setIsDelete(0);
            list = as.getAdmin(sysAdminQuery, level);
            count = as.getAdminCount(sysAdminQuery, level);

        } catch (Exception e) {
            logger.error("管理员或者数量获取失败", e);
        }
        if (count < 0) {
            return new DataJson(-1, "获取失败");
        }

        return new DataJson(0, "获取成功", list, count);


    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    @MyAnnotation({"adminManage"})
    public DataJson loginUser(String password, String adminName, HttpServletResponse response) {
        if (StringUtils.isEmpty(adminName)) {
            return new DataJson(300, "用户名不得为空");
        }
        if (StringUtils.isEmpty(password)) {
            return new DataJson(300, "密码不得为空");
        }

        List<SysAdmin> list = new ArrayList<>();

        SysAdminQuery sysAdminQuery = new SysAdminQuery();

        sysAdminQuery.setAdminName(adminName);
        sysAdminQuery.setIsDelete(0);

        try {
            Integer level = null;
            list = as.getAdmin(sysAdminQuery, level);

            if (list.size() == 0) {
                return new DataJson(300, "该账户不存在");
            }

            if (list.get(0).getIsLock() == 1) {
                return new DataJson(300, "该账号已锁定，请联系管理员解锁");
            } else if (list.get(0).getIsLock() == 0) {
                String pw = MD5Util.GetMD5Code(password);
                if (!pw.equals(list.get(0).getPassword())) {

                    //锁定次数
                    int count = list.get(0).getLockCount();
                    list.get(0).setLockCount(count + 1);
                    as.updateAdmin(list.get(0));

                    if (list.get(0).getLockCount() == 5) {
                        list.get(0).setIsLock(1);
                        as.updateAdmin(list.get(0));
                    }

                    return new DataJson(300, "密码错误,还有" + (5 - count) + "次将被锁定");

                }
            }

        } catch (Exception e) {
            logger.error("登陆失败", e);
        }

        try {
            list.get(0).setIsLock(0);
            list.get(0).setLockCount(0);
            list.get(0).setLoginTime(new Date());
            as.updateAdmin(list.get(0));
        } catch (Exception e) {
            logger.error("修改锁定失败", e);
        }

        try {
            SysRole sysRole = rs.selectRoleById(list.get(0).getRoleId());

            List<SysMenu> menuList = ms.getSysMenus(new SysMenu(), list.get(0).getRoleId());

            List<String> htmlList = phs.getPowerHtmlByMenus(menuList);

            WebRedisJson updateRedis = new WebRedisJson();
            updateRedis.setSysAdmin(list.get(0));
            updateRedis.setSysRole(sysRole);
            updateRedis.setSysMenu(menuList);
            updateRedis.setSysPowerHtml(htmlList);

            Util.addCookie(response, "adminName", list.get(0).getAdminName(), 3);

            RedisUtil.set(list.get(0).getAdminName(), updateRedis, 60 * 60 * 3);
            System.out.println(RedisUtil.get(list.get(0).getAdminName()));
        } catch (Exception e) {
            logger.error("redis写入失败", e);
        }

//        }
        return new DataJson(200, "登陆成功");

    }

    @PostMapping("/addAdmin")
    @MyAnnotation({"adminManage"})
    @ResponseBody
    public DataJson addAdmin(SysAdmin sysAdmin, HttpServletRequest request) {
        try {
            sysAdmin.setIsDelete(0);
            String adminName = Util.getCookie(request, "adminName");
//            WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(adminName);
//            Integer level = webRedisJson.getSysRole().getLevel();
            sysAdmin.setCreatePer(adminName);

            sysAdmin.setPassword(MD5Util.GetMD5Code(sysAdmin.getPassword()));
            sysAdmin.setCreateTime(new Date());
            sysAdmin.setLockCount(0);
//            sysAdmin.setIsLock(0);
            int count = as.addAdmin(sysAdmin);
            if (count == 2) {
                return new DataJson(300, "该管理员已存在！");
            } else if (count == -1) {
                return new DataJson(500, "服务器异常，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("管理员新增失败", e);
        }
        return new DataJson(200, "新增成功");
    }

    @PostMapping("/delAdmin")
    @MyAnnotation({"adminManage"})
    @ResponseBody
    public DataJson delAdmin(SysAdmin sysAdmin) {
        int count = 0;
        try {
            sysAdmin.setIsDelete(1);
            count = as.deleteAdmin(sysAdmin);
            if (count < 0) {
                return new DataJson(500, "服务器异常，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "删除失败");
            }
        } catch (Exception e) {
            logger.error("管理员删除失败", e);
        }
        return new DataJson(200, "删除成功");
    }

    @PostMapping("/editAdmin")
    @MyAnnotation({"adminManage"})
    @ResponseBody
    public DataJson editAdmin(SysAdmin sysAdmin, HttpServletRequest request) {
        int count = 0;
        sysAdmin.setModifyPer(Util.getCookie(request, "adminName"));
        sysAdmin.setPassword(MD5Util.GetMD5Code(sysAdmin.getPassword()));
        try {
            count = as.updateAdmin(sysAdmin);
            if (count < 0) {
                return new DataJson(500, "服务器出错，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count == 2) {
                return new DataJson(300, "该管理员已存在，请重试");
            }
        } catch (Exception e) {
            logger.error("修改管理员信息失败", e);
        }

        return new DataJson(200, "修改成功");
    }


    @GetMapping("/getvalue")
    public void getvalue() {
        System.out.println(RedisUtil.get("连工"));
    }

}
