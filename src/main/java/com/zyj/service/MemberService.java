package com.zyj.service;

import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;


import java.util.List;

public interface MemberService {
    public List<HotelMember> getHotelMember(HotelMemberQuery hotelMemberQuery) throws Exception;

    public int addHotelMember(HotelMember HotelMember) throws Exception;

    public int updateHotelMember(HotelMember HotelMember) throws Exception;

    public int delHotelMember(HotelMember HotelMember) throws Exception;

    public int getHotelMemberCount(HotelMemberQuery hotelMemberQuery) throws Exception;

    public String getMemberForApply(int memberId) throws Exception;
}
