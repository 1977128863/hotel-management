package com.zyj.service.impl;

import com.zyj.dao.SysAdminDao;
import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.service.SysAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysAdminServiceImpl implements SysAdminService {
    private Logger logger = LogManager.getLogger(SysAdminServiceImpl.class);

    @Autowired
    private SysAdminDao ad;

    @Override
    @Transactional
    public List<SysAdmin> getAdmin(SysAdminQuery sysAdminQuery, Integer level) throws Exception {
        List<SysAdmin> list = new ArrayList<SysAdmin>();

        try {
            list = ad.getAdmin(sysAdminQuery, level);
        } catch (Exception e) {
            logger.error("管理员获取失败", e);
        }

        return list;
    }

    @Override
    @Transactional
    public int addAdmin(SysAdmin sysAdmin) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            id = ad.getAdminIdByAdminName(sysAdmin.getAdminName());
            if (id == null || id == sysAdmin.getAdminId()) {
                count = ad.insertSelective(sysAdmin);
            } else {
                return 2;
            }

        } catch (Exception e) {
            logger.error("管理员新增失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int deleteAdmin(SysAdmin sysAdmin) throws Exception {
        int count = 0;
        try {
            count = ad.deleteAdmin(sysAdmin);
        } catch (Exception e) {
            logger.error("删除管理员失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int updateAdmin(SysAdmin sysAdmin) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            id = ad.getAdminIdByAdminName(sysAdmin.getAdminName());
            if (id == null || id == sysAdmin.getAdminId()) {
                count = ad.updateByPrimaryKeySelective(sysAdmin);
            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("修改管理员信息失败", e);
        }

        return count;
    }

    @Override
    public int batchDeleteAdmin(List<Integer> ids) throws Exception {
        return 0;
    }

    @Override
    @Transactional
    public int getAdminCount(SysAdminQuery sysAdminQuery, Integer level) throws Exception {
        int count = 0;

        try {
            count = ad.getAdminCount(sysAdminQuery, level);
        } catch (Exception e) {
            logger.error("管理员数量查询失败", e);
        }

        return count;
    }

}
