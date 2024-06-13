package com.zyj.service.impl;

import com.zyj.dao.HotelUserMapper;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.User.HotelUser;
import com.zyj.dto.User.HotelUserQuery;
import com.zyj.service.SysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    private static Logger logger = LogManager.getLogger(SysUserServiceImpl.class);

    @Autowired
    private HotelUserMapper um;

    @Override
    @Transactional
    public List<HotelUser> getUser(HotelUserQuery hotelUserQuery) throws Exception {
        List<HotelUser> list = new ArrayList<HotelUser>();

        try {
            list = um.getUser(hotelUserQuery);
        } catch (Exception e) {
            logger.error("用户获取失败", e);
        }

        return list;
    }

    @Override
    public int addUser(HotelUser hotelUser) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            HotelUserQuery hotelUserQuery = new HotelUserQuery();
            hotelUserQuery.setNickName(hotelUser.getNickName());
            hotelUserQuery.setIsDelete(0);
            count = um.getUserCount(hotelUserQuery);
            if (count > 0) {
                return 2;
            } else if (count < 0) {
                return -1;
            } else {
                count = um.insertSelective(hotelUser);
            }
        } catch (Exception e) {
            logger.error("用户新增失败", e);
        }

        return count;
    }

    @Override
    public int deleteUser(HotelUser hotelUser) throws Exception {
        int count = 0;
        try {
            count = um.deleteUser(hotelUser);
        } catch (Exception e) {
            logger.error("删除用户失败", e);
        }

        return count;
    }

    @Override
    public int updateUser(HotelUser hotelUser) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            id = um.getUserIdByNickName(hotelUser.getNickName());
            if (id == null || id == hotelUser.getUserId()) {
                count = um.updateByPrimaryKeySelective(hotelUser);
            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("修改用户信息失败", e);
        }

        return count;
    }

    @Override
    public int batchDeleteUser(List<Integer> ids) throws Exception {
        return 0;
    }

    @Override
    public int getUserCount(HotelUserQuery hotelUserQuery) throws Exception {
        int count = 0;

        try {
            count = um.getUserCount(hotelUserQuery);
        } catch (Exception e) {
            logger.error("用户数量查询失败", e);
        }

        return count;
    }
}
