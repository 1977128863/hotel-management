package com.zyj.dao;


import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.User.HotelUser;
import com.zyj.dto.User.HotelUserQuery;

import java.util.List;

public interface HotelUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(HotelUser record);

    int insertSelective(HotelUser record);

    HotelUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(HotelUser record);

    int updateByPrimaryKey(HotelUser record);

    List<HotelUser> getUser(HotelUserQuery hotelUserQuery);

    Integer getUserCount(HotelUserQuery hotelUserQuery);

    int deleteUser(HotelUser hotelUser);

    Integer getUserIdByNickName(String nickName);
}