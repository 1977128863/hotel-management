package com.zyj.service;

import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;

import java.util.List;

public interface SysDataService {
    public List<String> getDataForApply(String key, int value) throws Exception;

    public List<SysData> getData(SysDataQuery sysDataQuery) throws Exception;

    public int updateData(SysData sysData) throws Exception;

    public int delData(SysData sysData) throws Exception;

    public int addData(SysData sysData) throws Exception;

    public int getDataCount(SysDataQuery sysDataQuery) throws Exception;
}
