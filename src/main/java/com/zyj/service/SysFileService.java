package com.zyj.service;

import com.zyj.dto.File.SysFile;
import com.zyj.dto.File.SysFileQuery;
import com.zyj.dto.HotelMember.HotelMember;
import com.zyj.dto.HotelMember.HotelMemberQuery;

import java.util.List;

public interface SysFileService {
    public List<SysFile> getFile(SysFileQuery sysFileQuery) throws Exception;

    public int addFile(SysFile sysFile) throws Exception;

    public int updateFile(SysFile sysFile) throws Exception;

    public int delFile(SysFile sysFile) throws Exception;
}
