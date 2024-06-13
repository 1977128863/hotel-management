package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.dto.roomClassify.HotelRoomClassify;
import com.zyj.dto.roomClassify.HotelRoomClassifyQuery;
import com.zyj.service.RoomClassifyService;
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
@RequestMapping("/back/roomClassify")
public class HotelRoomClassifyController {
    private static Logger logger = LogManager.getLogger(HotelRoomClassifyController.class);

    @Autowired
    private RoomClassifyService rs;

    @PostMapping("/getRoomClassify")
    @ResponseBody
    public DataJson<HotelRoomClassify> getRoomClassify(HotelRoomClassifyQuery hotelRoomClassifyQuery) {

        if (hotelRoomClassifyQuery.getPage() != null && hotelRoomClassifyQuery.getLimit() != null) {
            int start = hotelRoomClassifyQuery.getLimit() * (hotelRoomClassifyQuery.getPage() - 1);
            hotelRoomClassifyQuery.setStart(start);
        }


        List<HotelRoomClassify> list = new ArrayList<>();
        int count = 0;
        try {
            list = rs.getRoomClassify(hotelRoomClassifyQuery);
            count = rs.getRoomClassifyCount(hotelRoomClassifyQuery);
            if (list.size() == 0 && count == 0) {
                return new DataJson(0, "暂无数据");
            } else if (count == 0 || list == null) {
                return new DataJson(-1, "获取房间类型获取失败");
            } else if (count < 0) {
                return new DataJson(-1, "系统错误，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("房间类型获取失败", e);
        }


        return new DataJson(0, "获取房间类型成功", list, count);
    }


    @PostMapping("/addRoomClassify")
    @ResponseBody
    public DataJson addRoomClassify(HotelRoomClassify hotelRoomClassify, HttpServletRequest request) {
        int count = 0;
        String adminName = Util.getCookie(request, "adminName");
        hotelRoomClassify.setCreatePer(adminName);
        hotelRoomClassify.setCreateTime(new Date());
        try {
            count = rs.addRoomClassify(hotelRoomClassify);
            if (count < 0) {
                return new DataJson(500, "系统错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "新增失败");

            }
        } catch (Exception e) {
            logger.error("房间类型新增失败", e);
        }


        return new DataJson(200, "增加成功");
    }

    @PostMapping("/editRoomClassify")
    @ResponseBody
    public DataJson editRoomClassify(HotelRoomClassify hotelRoomClassify, HttpServletRequest request) {
        int count = 0;
        String modifyPer = Util.getCookie(request, "adminName");
        hotelRoomClassify.setModifyPer(modifyPer);
        hotelRoomClassify.setModifyTime(new Date());
        try {
            count = rs.updateRoomClassify(hotelRoomClassify);
            if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count < 0) {
                return new DataJson(500, "系统错误请联系管理员");
            } else if (count == 2) {
                return new DataJson(300, "该房间分类已存在");
            }
        } catch (Exception e) {
            logger.error("修改失败", e);
        }


        return new DataJson(200, "修改成功");
    }

    @PostMapping("/delRoomClassify")
    @ResponseBody
    public DataJson delRoomClassify(HotelRoomClassify hotelRoomClassify) {
        int count = 0;

        try {
            count = rs.delRoomClassify(hotelRoomClassify);
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

    @PostMapping("/getAllClassify")
    @ResponseBody
    public DataJson<HotelRoomClassify> getAllClassify() {
        List<HotelRoomClassify> list = new ArrayList<>();
        try {
            list = rs.getAllClassify();
            if (list == null) {
                return new DataJson(300, "获取全部分类失败");
            }
        } catch (Exception e) {
            logger.error("获取全部房间分类失败", e);
        }

        return new DataJson(200, "获取全部分类成功", list, list.size());
    }
}
