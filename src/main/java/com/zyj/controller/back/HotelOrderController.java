package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;
import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.Order.HotelOrderQuery;
import com.zyj.service.MemberService;
import com.zyj.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/back/order")
public class HotelOrderController {
    private static Logger logger = LogManager.getLogger(HotelOrderController.class);

    @Autowired
    private OrderService os;


    @PostMapping("/getHotelOrder")
    @ResponseBody
    public DataJson<HotelMember> getHotelOrder(HotelOrderQuery hotelOrderQuery) {

        if (hotelOrderQuery.getPage() != null && hotelOrderQuery.getLimit() != null) {
            int start = hotelOrderQuery.getLimit() * (hotelOrderQuery.getPage() - 1);
            hotelOrderQuery.setStart(start);
        }


        List<HotelOrder> list = new ArrayList<>();
        int count = 0;
        try {
            list = os.getHotelOrder(hotelOrderQuery);
            count = os.getHotelOrderCount(hotelOrderQuery);
            if (list.size() == 0 && count == 0) {
                return new DataJson(0, "暂无数据");
            } else if (count == 0 || list == null) {
                return new DataJson(-1, "订单获取失败");
            } else if (count < 0) {
                return new DataJson(-1, "系统错误，请联系管理员");
            }
        } catch (Exception e) {
            logger.error("订单获取失败", e);
        }


        return new DataJson(0, "获取订单成功", list, count);
    }

    @PostMapping("/addOrder")
    @ResponseBody
    public DataJson addOrder(HotelOrder hotelOrder) {
        int count = 0;
        try {
            count = os.addHotelOrder(hotelOrder);
            if (count < 0) {
                return new DataJson(500, "系统错误，请联系管理员");
            } else if (count == 0) {
                return new DataJson(300, "新增失败");

            }
        } catch (Exception e) {
            logger.error("订单新增失败", e);
        }


        return new DataJson(200, "新增成功");
    }

    @PostMapping("/editOrder")
    @ResponseBody
    public DataJson editOrder(HotelOrder hotelOrder) {
        int count = 0;

        try {
            count = os.updateHotelOrder(hotelOrder);
//            if (count == 0){
//                return new DataJson(300,"修改失败");
//            }else if(count < 0){
//                return new DataJson(500,"系统错误请联系管理员");
//            }else if (count == 2){
//                return new DataJson(300,"该会员已存在");
//            }
        } catch (Exception e) {
            logger.error("修改失败", e);
        }


        return new DataJson(200, "修改成功");
    }

    @PostMapping("/delOrder")
    @ResponseBody
    public DataJson delOrder(HotelOrder hotelOrder) {
        int count = 0;

        try {
            count = os.delHotelOrder(hotelOrder);
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
