package com.zyj.service;

import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;
import com.zyj.dto.DataItem.SysDataItem;
import com.zyj.dto.DataItem.SysDataItemQuery;

import java.util.List;

public interface SysDataItemService {

    public List<SysDataItem> getDataItem(SysDataItemQuery sysDataItemQuery) throws Exception;

    public int updateDataItem(SysDataItem sysDataItem) throws Exception;

    public int delDataItem(SysDataItem sysDataItem) throws Exception;

    public int addDataItem(SysDataItem sysDataItem) throws Exception;

    public int getDataCountItem(SysDataItemQuery sysDataItemQuery) throws Exception;

}
