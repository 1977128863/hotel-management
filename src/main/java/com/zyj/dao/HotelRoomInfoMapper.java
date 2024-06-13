package com.zyj.dao;


import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.Order.HotelOrderQuery;
import com.zyj.dto.roomInfo.HotelRoomInfo;
import com.zyj.dto.roomInfo.HotelRoomInfoQuery;

import java.util.List;

public interface HotelRoomInfoMapper {
    int deleteByPrimaryKey(Integer roomId);

    int insert(HotelRoomInfo record);

    int insertSelective(HotelRoomInfo record);

    HotelRoomInfo selectByPrimaryKey(Integer roomId);

    int updateByPrimaryKeySelective(HotelRoomInfo record);

    int updateByPrimaryKey(HotelRoomInfo record);

    List<HotelRoomInfo> getHotelInfo(HotelRoomInfoQuery hotelRoomInfoQuery);

    int getHotelInfoCount(HotelRoomInfoQuery hotelRoomInfoQuery);

    HotelRoomInfo getRoomInfoIdByRoomCode(String roomCode);
}
