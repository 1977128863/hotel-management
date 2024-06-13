package com.zyj.dao;


import com.zyj.dto.roomClassify.HotelRoomClassify;
import com.zyj.dto.roomClassify.HotelRoomClassifyQuery;

import java.util.List;

public interface HotelRoomClassifyMapper {
    int deleteByPrimaryKey(Integer classifyId);

    int insert(HotelRoomClassify record);

    int insertSelective(HotelRoomClassify record);

    HotelRoomClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(HotelRoomClassify record);

    int updateByPrimaryKey(HotelRoomClassify record);

    List<HotelRoomClassify> getRoomClassify(HotelRoomClassifyQuery hotelRoomClassifyQuery);

    int getRoomClassifyCount(HotelRoomClassifyQuery hotelRoomClassifyQuery);

    int getClassifyIdByName(String classifyName);

    int delClassifyByTopId(Integer topClassifyId);

    List<HotelRoomClassify> getAllClassify();
}