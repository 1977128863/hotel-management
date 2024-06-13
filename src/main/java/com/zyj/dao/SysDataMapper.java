package com.zyj.dao;


import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDataMapper {
    int deleteByPrimaryKey(Integer dataId);

    int insert(SysData record);

    int insertSelective(SysData record);

    SysData selectByPrimaryKey(Integer dataId);

    int updateByPrimaryKeySelective(SysData record);

    int updateByPrimaryKey(SysData record);

    List<String> getDataForApply(@Param("key") String key, @Param("value") int value);

    List<SysData> getData(SysDataQuery sysDataQuery);

    int getDataCount(SysDataQuery sysDataQuery);

}