package com.zyj.service.impl;

import com.zyj.dao.SysFileMapper;
import com.zyj.dto.File.SysFile;
import com.zyj.dto.File.SysFileQuery;
import com.zyj.dto.roomInfo.HotelRoomInfo;
import com.zyj.service.SysFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysFileServiceImpl implements SysFileService {
    private static Logger logger = LogManager.getLogger(SysFileServiceImpl.class);

    @Autowired
    private SysFileMapper fm;

    @Override
    public List<SysFile> getFile(SysFileQuery sysFileQuery) throws Exception {
        List<SysFile> list = new ArrayList<SysFile>();
        try {
            list = fm.getFile(sysFileQuery);
        } catch (Exception e) {
            logger.error("获取文件失败", e);
        }
        return list;
    }

    @Override
    public int addFile(SysFile sysFile) throws Exception {
        int count = 0;

        try {
            count = fm.insertSelective(sysFile);
        } catch (Exception e) {
            logger.error("文件新增失败", e);
        }
        return count;
    }

    @Override
    public int updateFile(SysFile sysFile) throws Exception {
        return 0;
    }

    @Override
    public int delFile(SysFile sysFile) throws Exception {
        return 0;
    }
}
