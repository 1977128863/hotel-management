package com.zyj.service;

import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.User.HotelUser;
import com.zyj.dto.User.HotelUserQuery;

import java.util.List;

public interface SysUserService {
    public List<HotelUser> getUser(HotelUserQuery hotelUserQuery) throws Exception;

    public int addUser(HotelUser hotelUser) throws Exception;

    public int deleteUser(HotelUser hotelUser) throws Exception;

    public int updateUser(HotelUser hotelUser) throws Exception;

    public int batchDeleteUser(List<Integer> ids) throws Exception;

    public int getUserCount(HotelUserQuery hotelUserQuery) throws Exception;
}
