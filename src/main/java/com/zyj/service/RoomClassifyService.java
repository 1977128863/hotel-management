package com.zyj.service;


import com.zyj.dto.roomClassify.HotelRoomClassify;
import com.zyj.dto.roomClassify.HotelRoomClassifyQuery;

import java.util.List;

public interface RoomClassifyService {
    public List<HotelRoomClassify> getRoomClassify(HotelRoomClassifyQuery hotelRoomClassifyQuery) throws Exception;

    public int addRoomClassify(HotelRoomClassify hotelRoomClassify) throws Exception;

    public int updateRoomClassify(HotelRoomClassify hotelRoomClassify) throws Exception;

    public int delRoomClassify(HotelRoomClassify hotelRoomClassify) throws Exception;

    public int getRoomClassifyCount(HotelRoomClassifyQuery hotelRoomClassifyQuery) throws Exception;

    public List<HotelRoomClassify> getAllClassify() throws Exception;

}
