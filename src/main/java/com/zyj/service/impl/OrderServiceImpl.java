package com.zyj.service.impl;

import com.zyj.dao.HotelMemberMapper;
import com.zyj.dao.HotelOrderMapper;
import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;
import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.Order.HotelOrderQuery;
import com.zyj.service.MemberService;
import com.zyj.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Autowired
    private HotelOrderMapper mm;

    @Override
    @Transactional
    public List<HotelOrder> getHotelOrder(HotelOrderQuery hotelOrderQuery) throws Exception {
        List<HotelOrder> list = new ArrayList<HotelOrder>();
        try {
            list = mm.getHotelOrder(hotelOrderQuery);
        } catch (Exception e) {
            logger.error("获取订单失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int addHotelOrder(HotelOrder hotelOrder) throws Exception {
        int count = 0;

        try {
            count = mm.insertSelective(hotelOrder);
        } catch (Exception e) {
            logger.error("订单新增失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int updateHotelOrder(HotelOrder hotelOrder) throws Exception {
        int count = 0;
        Integer id = null;
        try {
//            id = mm.getMemberByName(HotelMember.getMemberName());
//            if (id == null || id == HotelMember.getMemberId()){
//                count = mm.updateByPrimaryKeySelective(HotelMember);
//            }else {
//                count = 2;
//            }

        } catch (Exception e) {
            logger.error("订单修改失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int delHotelOrder(HotelOrder hotelOrder) throws Exception {
        int count = 0;

        try {
            count = mm.deleteByPrimaryKey(hotelOrder.getOrderId());
        } catch (Exception e) {
            logger.error("订单删除失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int getHotelOrderCount(HotelOrderQuery hotelOrderQuery) throws Exception {
        int count = 0;
        try {
            count = mm.getHotelOrderCount(hotelOrderQuery);
        } catch (Exception e) {
            logger.error("订单数量获取失败", e);
        }
        return count;
    }
}
