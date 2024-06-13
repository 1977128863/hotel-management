package com.zyj.dao;

import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;
import com.zyj.dto.roomClassify.HotelRoomClassify;
import com.zyj.dto.roomClassify.HotelRoomClassifyQuery;

import java.util.List;

public interface HotelMemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(HotelMember record);

    int insertSelective(HotelMember record);

    HotelMember selectByPrimaryKey(Integer memberId);

    int updateByPrimaryKeySelective(HotelMember record);

    int updateByPrimaryKey(HotelMember record);

    List<HotelMember> getHotelMember(HotelMemberQuery hotelMemberQuery);

    int getHotelMemberCount(HotelMemberQuery hotelMemberQuery);

    int getMemberByName(String memberName);

    String getMemberForApply(int memberId);
}