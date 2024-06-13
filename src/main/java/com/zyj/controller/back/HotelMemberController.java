package com.zyj.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.zyj.common.DataJson;
import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;
import com.zyj.dto.roomClassify.HotelRoomClassify;
import com.zyj.dto.roomClassify.HotelRoomClassifyQuery;
import com.zyj.service.MemberService;
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
@RequestMapping("/back/member")
public class HotelMemberController {
    private static Logger logger = LogManager.getLogger(HotelMemberController.class);

    @Autowired
    private MemberService ms;


    @PostMapping("/getHotelMember")
    @ResponseBody
    public DataJson<HotelMember> getHotelMember(HotelMemberQuery hotelMemberQuery) {

        if (hotelMemberQuery.getPage() != null && hotelMemberQuery.getLimit() != null) {
            int start = hotelMemberQuery.getLimit() * (hotelMemberQuery.getPage() - 1);
            hotelMemberQuery.setStart(start);
        }


        List<HotelMember> list = new ArrayList<>();
        int count = 0;
        try {
            list = ms.getHotelMember(hotelMemberQuery);
            count = ms.getHotelMemberCount(hotelMemberQuery);
            if (list.size() == 0 && count == 0) {
                return new DataJson(0, "暂无数据");
            } else if (count == 0 || list == null) {
                return new DataJson(-1, "会员获取失败");
            } else if (count < 0) {
                return new DataJson(-1, "系统错误，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("会员获取失败", e);
        }


        return new DataJson(0, "获取会员成功", list, count);
    }

    @PostMapping("/addMember")
    @ResponseBody
    public DataJson addMember(HotelMember member) {
        int count = 0;
        try {
            count = ms.addHotelMember(member);
            if (count < 0) {
                return new DataJson(500, "系统错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "新增失败");

            }
        } catch (Exception e) {
            logger.error("会员新增失败", e);
        }


        return new DataJson(200, "新增成功");
    }

    @PostMapping("/editMember")
    @ResponseBody
    public DataJson editMember(HotelMember hotelMember) {
        int count = 0;

        try {
            count = ms.updateHotelMember(hotelMember);
            if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count < 0) {
                return new DataJson(500, "系统错误请联系管理员");
            } else if (count == 2) {
                return new DataJson(300, "该会员已存在");
            }
        } catch (Exception e) {
            logger.error("修改失败", e);
        }


        return new DataJson(200, "修改成功");
    }

    @PostMapping("/delMember")
    @ResponseBody
    public DataJson delMember(HotelMember hotelMember) {
        int count = 0;

        try {
            count = ms.delHotelMember(hotelMember);
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

    @PostMapping("/getMemberForApply")
    @ResponseBody
    public JSONObject getMemberForApply(int memberId) {
        String str = "";
        JSONObject jsonObject = new JSONObject();
        try {
            str = ms.getMemberForApply(memberId);
            if (str == "") {
                jsonObject.put("code", 300);
                jsonObject.put("message", "会员获取失败");
                return jsonObject;
            } else if (str == null) {
                jsonObject.put("code", 500);
                jsonObject.put("message", "系统错误，请联系管理员");
                return jsonObject;
            }
        } catch (Exception e) {
            logger.error("会员获取失败", e);
        }

        jsonObject.put("code", 200);
        jsonObject.put("message", "会员获取成功");
        jsonObject.put("str", str);
        return jsonObject;
    }


}
