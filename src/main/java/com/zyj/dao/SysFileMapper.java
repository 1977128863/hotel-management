package com.zyj.dao;


import com.zyj.dto.File.SysFile;
import com.zyj.dto.File.SysFileQuery;

import java.util.List;

public interface SysFileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(SysFile record);

    int insertSelective(SysFile record);

    SysFile selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(SysFile record);

    int updateByPrimaryKey(SysFile record);

    List<SysFile> getFile(SysFileQuery sysFileQuery);
}