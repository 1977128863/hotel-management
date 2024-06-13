package com.zyj.dao;

import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.Order.HotelOrderQuery;

import java.util.List;

public interface HotelOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(HotelOrder record);

    int insertSelective(HotelOrder record);

    HotelOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(HotelOrder record);

    int updateByPrimaryKey(HotelOrder record);

    List<HotelOrder> getHotelOrder(HotelOrderQuery hotelOrderQuery);

    int getHotelOrderCount(HotelOrderQuery hotelOrderQuery);

    int getRoomStatusByRoomCode(String menuCode);
}