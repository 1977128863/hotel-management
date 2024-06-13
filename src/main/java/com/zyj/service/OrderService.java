package com.zyj.service;

import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;
import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.Order.HotelOrderQuery;

import java.util.List;

public interface OrderService {
    public List<HotelOrder> getHotelOrder(HotelOrderQuery hotelOrderQuery) throws Exception;

    public int addHotelOrder(HotelOrder hotelOrder) throws Exception;

    public int updateHotelOrder(HotelOrder hotelOrder) throws Exception;

    public int delHotelOrder(HotelOrder hotelOrder) throws Exception;

    public int getHotelOrderCount(HotelOrderQuery hotelOrderQuery) throws Exception;
}
