package com.zyj.service.impl;

import com.zyj.dao.HotelRoomInfoMapper;
import com.zyj.dto.Order.HotelOrder;
import com.zyj.dto.roomInfo.HotelRoomInfo;
import com.zyj.dto.roomInfo.HotelRoomInfoQuery;
import com.zyj.service.RoomInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {
    private static Logger logger = LogManager.getLogger(RoomInfoServiceImpl.class);

    @Autowired
    private HotelRoomInfoMapper rim;

    @Override
    @Transactional
    public List<HotelRoomInfo> getHotelInfo(HotelRoomInfoQuery hotelRoomInfoQuery) throws Exception {
        List<HotelRoomInfo> list = new ArrayList<HotelRoomInfo>();
        try {
            list = rim.getHotelInfo(hotelRoomInfoQuery);
        } catch (Exception e) {
            logger.error("获取房间信息失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int addHotelInfo(HotelRoomInfo hotelRoomInfo) throws Exception {
        int count = 0;

        try {
            count = rim.insertSelective(hotelRoomInfo);
        } catch (Exception e) {
            logger.error("房间新增失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int updateHotelInfo(HotelRoomInfo hotelRoomInfo) throws Exception {
        int count = 0;
        HotelRoomInfo hotelRoomInfo1 = new HotelRoomInfo();
        try {
            hotelRoomInfo1 = rim.getRoomInfoIdByRoomCode(hotelRoomInfo.getRoomCode());
            if (hotelRoomInfo1 == null || hotelRoomInfo1.getRoomId() == hotelRoomInfo.getRoomId()) {
                count = rim.updateByPrimaryKeySelective(hotelRoomInfo);
            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("房间信息修改失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int delHotelInfo(HotelRoomInfo hotelRoomInfo) throws Exception {
        int count = 0;

        try {
            count = rim.deleteByPrimaryKey(hotelRoomInfo.getRoomId());
        } catch (Exception e) {
            logger.error("房间信息删除失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int getHotelInfoCount(HotelRoomInfoQuery hotelRoomInfoQuery) throws Exception {
        int count = 0;
        try {
            count = rim.getHotelInfoCount(hotelRoomInfoQuery);
        } catch (Exception e) {
            logger.error("房间信息数量获取失败", e);
        }
        return count;
    }
}
