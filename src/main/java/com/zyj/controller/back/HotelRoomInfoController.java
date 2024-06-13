package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.roomInfo.HotelRoomInfo;
import com.zyj.dto.roomInfo.HotelRoomInfoQuery;
import com.zyj.service.RoomInfoService;
import com.zyj.service.impl.RoomInfoServiceImpl;
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
@RequestMapping("/back/roomInfo")
public class HotelRoomInfoController {
    private static Logger logger = LogManager.getLogger(HotelRoomInfoController.class);

    @Autowired
    private RoomInfoService rs;

    @PostMapping("/getRoomInfo")
    @ResponseBody
    public DataJson<HotelRoomInfo> getRoomInfo(HotelRoomInfoQuery hotelRoomInfoQuery) {

        if (hotelRoomInfoQuery.getPage() != null && hotelRoomInfoQuery.getLimit() != null) {
            int start = hotelRoomInfoQuery.getLimit() * (hotelRoomInfoQuery.getPage() - 1);
            hotelRoomInfoQuery.setStart(start);
        }

        List<HotelRoomInfo> list = new ArrayList<HotelRoomInfo>();

        int count = 0;
        try {
            list = rs.getHotelInfo(hotelRoomInfoQuery);
            count = rs.getHotelInfoCount(hotelRoomInfoQuery);
            if (list.size() == 0 && count == 0) {
                return new DataJson(0, "暂无数据");
            } else if (count == 0 || list == null) {
                return new DataJson(-1, "房间信息获取失败");
            } else if (count < 0) {
                return new DataJson(-1, "系统错误，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("房间信息获取失败", e);
        }


        return new DataJson(0, "获取房间信息成功", list, count);
    }

    @PostMapping("/addRoomInfo")
    @ResponseBody
    public DataJson addRoomInfo(HotelRoomInfo hotelRoomInfo, HttpServletRequest request) {
        int count = 0;
        String adminName = Util.getCookie(request, "adminName");
        hotelRoomInfo.setCreatePer(adminName);
        hotelRoomInfo.setCreateTime(new Date());
        hotelRoomInfo.setIsDelete(0);

        try {
            count = rs.addHotelInfo(hotelRoomInfo);
            if (count < 0) {
                return new DataJson(500, "系统错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "新增失败");

            }
        } catch (Exception e) {
            logger.error("房间新增失败", e);
        }


        return new DataJson(200, "新增成功");
    }

    @PostMapping("/editRoomInfo")
    @ResponseBody
    public DataJson editRoomInfo(HotelRoomInfo hotelRoomInfo, HttpServletRequest request) {
        int count = 0;
        String modifyName = Util.getCookie(request, "adminName");
        hotelRoomInfo.setModifyPer(modifyName);
        hotelRoomInfo.setModifyTime(new Date());
        try {
            count = rs.updateHotelInfo(hotelRoomInfo);
            if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count < 0) {
                return new DataJson(500, "系统错误请联系管理员");
            } else if (count == 2) {
                return new DataJson(300, "该编号房间已存在，请重试");
            }
        } catch (Exception e) {
            logger.error("修改失败", e);
        }


        return new DataJson(200, "修改成功");
    }

    @PostMapping("/delRoomInfo")
    @ResponseBody
    public DataJson delRoomInfo(HotelRoomInfo hotelRoomInfo) {
        int count = 0;

        try {
            count = rs.delHotelInfo(hotelRoomInfo);
            if (count == 0) {
                return new DataJson(300, "删除失败");
            } else if (count < 0) {
                return new DataJson(500, "系统错误请联系管理员");
            }
        } catch (Exception e) {
            logger.error("删除失败", e);
        }


        return new DataJson(200, "删除成功");
    }


}
