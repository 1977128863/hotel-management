package com.zyj.service.impl;

import com.zyj.dao.HotelRoomClassifyMapper;
import com.zyj.dao.HotelRoomInfoMapper;
import com.zyj.dto.roomClassify.HotelRoomClassify;
import com.zyj.dto.roomClassify.HotelRoomClassifyQuery;
import com.zyj.service.RoomClassifyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomClassifyServiceImpl implements RoomClassifyService {
    private static Logger logger = LogManager.getLogger(RoomClassifyServiceImpl.class);

    @Autowired
    private HotelRoomClassifyMapper rm;

    @Override
    @Transactional
    public List<HotelRoomClassify> getRoomClassify(HotelRoomClassifyQuery hotelRoomClassifyQuery) throws Exception {
        List<HotelRoomClassify> list = new ArrayList<HotelRoomClassify>();
        try {
            list = rm.getRoomClassify(hotelRoomClassifyQuery);
        } catch (Exception e) {
            logger.error("获取房间类型失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int addRoomClassify(HotelRoomClassify hotelRoomClassify) throws Exception {
        int count = 0;

        try {
            count = rm.insertSelective(hotelRoomClassify);
        } catch (Exception e) {
            logger.error("房间类型新增失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int updateRoomClassify(HotelRoomClassify hotelRoomClassify) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            id = rm.getClassifyIdByName(hotelRoomClassify.getClassifyName());
            if (id == null || id == hotelRoomClassify.getClassifyId()) {
                count = rm.updateByPrimaryKeySelective(hotelRoomClassify);
            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("房间类型修改失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int delRoomClassify(HotelRoomClassify hotelRoomClassify) throws Exception {
        int count = 0;

        try {
            if (hotelRoomClassify.getClassifyId() != null) {
                count = rm.delClassifyByTopId(hotelRoomClassify.getClassifyId());
                if (count == 0) {
                    count = rm.deleteByPrimaryKey(hotelRoomClassify.getClassifyId());
                } else {
                    count = rm.deleteByPrimaryKey(hotelRoomClassify.getClassifyId());
                }
            } else {
                return 0;
            }


        } catch (Exception e) {
            logger.error("房间类型删除", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int getRoomClassifyCount(HotelRoomClassifyQuery hotelRoomClassifyQuery) throws Exception {
        int count = 0;
        try {
            count = rm.getRoomClassifyCount(hotelRoomClassifyQuery);
        } catch (Exception e) {
            logger.error("房间类型数量获取失败", e);
        }
        return count;
    }

    @Override
    public List<HotelRoomClassify> getAllClassify() throws Exception {
        List<HotelRoomClassify> list = new ArrayList<>();

        try {
            list = rm.getAllClassify();
        } catch (Exception e) {
            logger.error("获取全部房间分类失败", e);
        }
        return list;
    }
}
