package com.zyj.service.impl;

import com.zyj.dao.SysRoleMapper;
import com.zyj.dto.Admin.SysAdminQuery;
import com.zyj.dto.Role.SysRole;
import com.zyj.dto.Role.SysRoleDto;
import com.zyj.dto.Role.SysRoleQuery;
import com.zyj.service.SysRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    private static Logger logger = LogManager.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleMapper sm;


    @Override
    @Transactional
    public SysRole selectRoleById(Integer roleId) throws Exception {
        SysRole sysRole = new SysRole();

        try {
            sysRole = sm.selectByPrimaryKey(roleId);
        } catch (Exception e) {
            logger.error("角色信息获取失败", e);
        }

        return sysRole;
    }

    @Override
    @Transactional
    public List<SysRole> getRole(SysRoleQuery sysRoleQuery, Integer level) throws Exception {
        List<SysRole> list = new ArrayList<SysRole>();
        try {
            list = sm.getRole(sysRoleQuery, level);
        } catch (Exception e) {
            logger.error("角色获取失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int addRole(SysRole sysRole, Integer level) throws Exception {
        int count = 0;

        try {
            SysRoleQuery sysRoleQuery = new SysRoleQuery();
            sysRoleQuery.setName(sysRole.getName());
            count = sm.getRoleCount(sysRoleQuery, level);
            if (count > 0) {
                return 2;
            } else if (count < 0) {
                return -1;
            } else {
                count = sm.insertSelective(sysRole);
            }
        } catch (Exception e) {
            logger.error("管理员新增失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int delRole(SysRole sysRole) throws Exception {
        int count = 0;
        try {
            count = sm.deleteByPrimaryKey(sysRole.getRoleId());
        } catch (Exception e) {
            logger.error("删除管理员失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int editRole(SysRole sysRole) throws Exception {
        int count = 0;
        Integer id = null;
        try {
            id = sm.getRoleIdByName(sysRole.getName());
            if (id == null || id == sysRole.getRoleId()) {
                count = sm.updateByPrimaryKeySelective(sysRole);
            } else {
                count = 2;
            }

        } catch (Exception e) {
            logger.error("修改角色信息失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public int getRoleCount(SysRoleQuery sysRoleQuery, Integer level) throws Exception {
        int count = 0;

        try {
            count = sm.getRoleCount(sysRoleQuery, level);
        } catch (Exception e) {
            logger.error("角色数量查询失败", e);
        }

        return count;
    }

    @Override
    @Transactional
    public List<SysRole> getLevelForApply(SysRole sysRole) throws Exception {
        List<SysRole> list = new ArrayList<>();
        try {
            list = sm.getLevelForApply(sysRole);
        } catch (Exception e) {
            logger.error("level值获取失败", e);
        }


        return list;
    }

    @Override
    public SysRole getRoleByRoleName(String roleName) {

        return sm.getRoleByRoleName(roleName);
    }

    @Override
    @Transactional
    public int updateRoleMenu(SysRoleDto sysRoleDto) {
        //先删掉原来的角色关联菜单记录，再新增现有的
        int count = sm.deleteByRoleId(sysRoleDto.getRoleId());
        if (count >= 0){
            count = sm.insertBatchRoleMenu(sysRoleDto.getMenuIds(),sysRoleDto.getRoleId());
        }
        return count;
    }


}
