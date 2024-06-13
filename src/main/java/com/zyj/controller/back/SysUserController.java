package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.User.HotelUser;
import com.zyj.dto.User.HotelUserQuery;
import com.zyj.service.SysUserService;
import com.zyj.util.MD5Util;
import com.zyj.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/user")
public class SysUserController {

    @Autowired
    private SysUserService us;

    private static Logger logger = LogManager.getLogger(SysUserController.class);

    @PostMapping("/getUser")
    @ResponseBody
    public DataJson getUser(HotelUserQuery hotelUserQuery) {
        List<HotelUser> list = new ArrayList<HotelUser>();
        int count = 0;
        try {
            if (hotelUserQuery.getLimit() != null && hotelUserQuery.getPage() != null) {
                int start = hotelUserQuery.getLimit() * (hotelUserQuery.getPage() - 1);
                hotelUserQuery.setStart(start);
            }


            hotelUserQuery.setIsDelete(0);
            list = us.getUser(hotelUserQuery);
            count = us.getUserCount(hotelUserQuery);

            if (count < 0) {
                return new DataJson(-1, "获取失败");
            }
        } catch (Exception e) {
            logger.error("用户或者数量获取失败", e);
        }


        return new DataJson(0, "获取成功", list, count);


    }

    @PostMapping("/addUser")
    @ResponseBody
    public DataJson addUser(HotelUser hotelUser) {
        try {
            hotelUser.setIsDelete(0);
            hotelUser.setPassword(MD5Util.GetMD5Code(hotelUser.getPassword()));
            hotelUser.setCreateTime(new Date());
            hotelUser.setIsLock(0);
            int count = us.addUser(hotelUser);
            if (count == 2) {
                return new DataJson(300, "该用户名已存在！");
            } else if (count == -1) {
                return new DataJson(500, "服务器异常，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("用户新增失败", e);
        }
        return new DataJson(200, "新增成功");
    }

    @PostMapping("/delUser")
    @ResponseBody
    public DataJson delUser(HotelUser hotelUser) {
        int count = 0;
        try {
            hotelUser.setIsDelete(1);
            count = us.deleteUser(hotelUser);
            if (count < 0) {
                return new DataJson(500, "服务器异常，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "删除失败");
            }
        } catch (Exception e) {
            logger.error("用户删除失败", e);
        }
        return new DataJson(200, "删除成功");
    }

    @PostMapping("/editUser")
    @ResponseBody
    public DataJson editUser(HotelUser hotelUser) {
        int count = 0;
        hotelUser.setPassword(MD5Util.GetMD5Code(hotelUser.getPassword()));
        try {
            count = us.updateUser(hotelUser);
            if (count < 0) {
                return new DataJson(500, "服务器出错，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count == 2) {
                return new DataJson(300, "该用户名已存在，请重试");
            }
        } catch (Exception e) {
            logger.error("修改用户信息失败", e);
        }

        return new DataJson(200, "修改成功");
    }

}
