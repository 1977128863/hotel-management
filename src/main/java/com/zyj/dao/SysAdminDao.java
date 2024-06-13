package com.zyj.dao;

import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAdminDao {
    int deleteByPrimaryKey(Integer adminId);

    int insert(SysAdmin record);

    int insertSelective(SysAdmin record);

    SysAdmin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(SysAdmin record);

    int updateByPrimaryKey(SysAdmin record);

    List<SysAdmin> getAdmin(@Param("sysAdminQuery") SysAdminQuery sysAdminQuery, @Param("level") Integer level);

    int getAdminCount(@Param("sysAdminQuery") SysAdminQuery sysAdminQuery, @Param("level") Integer level);

    int deleteAdmin(SysAdmin sysAdmin);

    Integer getAdminIdByAdminName(String adminName);

    SysAdmin getAdminByAdminName(SysAdminQuery sysAdminQuery);
}
