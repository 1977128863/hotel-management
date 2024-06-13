package com.zyj.service;

import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.Order.HotelOrderQuery;
import com.zyj.dto.roomInfo.HotelRoomInfo;
import com.zyj.dto.roomInfo.HotelRoomInfoQuery;

import java.util.List;

public interface RoomInfoService {
    public List<HotelRoomInfo> getHotelInfo(HotelRoomInfoQuery hotelRoomInfoQuery) throws Exception;

    public int addHotelInfo(HotelRoomInfo hotelRoomInfo) throws Exception;

    public int updateHotelInfo(HotelRoomInfo hotelRoomInfo) throws Exception;

    public int delHotelInfo(HotelRoomInfo hotelRoomInfo) throws Exception;

    public int getHotelInfoCount(HotelRoomInfoQuery hotelRoomInfoQuery) throws Exception;
}
