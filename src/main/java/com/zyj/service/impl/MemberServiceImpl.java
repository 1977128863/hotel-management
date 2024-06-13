package com.zyj.service.impl;

import com.zyj.dao.HotelMemberMapper;
import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;
import com.zyj.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private static Logger logger = LogManager.getLogger(MemberServiceImpl.class);

    @Autowired
    private HotelMemberMapper mm;

    @Override
    @Transactional
    public List<HotelMember> getHotelMember(HotelMemberQuery hotelMemberQuery) throws Exception {
        List<HotelMember> list = new ArrayList<HotelMember>();
        try {
            list = mm.getHotelMember(hotelMemberQuery);
        } catch (Exception e) {
            logger.error("获取会员失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int addHotelMember(HotelMember HotelMember) throws Exception {
        int count = 0;

        try {
            count = mm.insertSelective(HotelMember);
        } catch (Exception e) {
            logger.error("会员新增失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int updateHotelMember(HotelMember HotelMember) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            id = mm.getMemberByName(HotelMember.getMemberName());
            if (id == null || id == HotelMember.getMemberId()) {
                count = mm.updateByPrimaryKeySelective(HotelMember);
            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("会员修改失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int delHotelMember(HotelMember HotelMember) throws Exception {
        int count = 0;

        try {
            count = mm.deleteByPrimaryKey(HotelMember.getMemberId());
        } catch (Exception e) {
            logger.error("会员删除失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int getHotelMemberCount(HotelMemberQuery hotelMemberQuery) throws Exception {
        int count = 0;
        try {
            count = mm.getHotelMemberCount(hotelMemberQuery);
        } catch (Exception e) {
            logger.error("会员数量获取失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public String getMemberForApply(int memberId) throws Exception {
        String str = "";
        try {
            str = mm.getMemberForApply(memberId);
        } catch (Exception e) {
            logger.error("会员获取失败", e);
        }


        return str;
    }

}
