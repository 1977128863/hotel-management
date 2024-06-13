package com.zyj.dao;


import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;
import com.zyj.dto.DataItem.SysDataItem;
import com.zyj.dto.DataItem.SysDataItemQuery;

import java.util.List;

public interface SysDataItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(SysDataItem record);

    int insertSelective(SysDataItem record);

    SysDataItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(SysDataItem record);

    int updateByPrimaryKey(SysDataItem record);

    List<SysDataItem> getDataItem(SysDataItemQuery sysDataItemQuery);

    int getDataItemCount(SysDataItemQuery sysDataItemQuery);

    int delDataItem(SysDataItem sysDataItem);
}