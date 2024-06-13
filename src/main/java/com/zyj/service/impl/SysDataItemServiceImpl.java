package com.zyj.service.impl;

import com.zyj.dao.SysDataItemMapper;
import com.zyj.dao.SysDataMapper;
import com.zyj.dto.Data.SysData;
import com.zyj.dto.DataItem.SysDataItem;
import com.zyj.dto.DataItem.SysDataItemQuery;
import com.zyj.service.SysDataItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDataItemServiceImpl implements SysDataItemService {
    private static Logger logger = LogManager.getLogger(SysDataItemServiceImpl.class);

    @Autowired
    private SysDataItemMapper dim;


    @Override
    public List<SysDataItem> getDataItem(SysDataItemQuery sysDataItemQuery) throws Exception {
        List<SysDataItem> list = new ArrayList<SysDataItem>();

        try {
            list = dim.getDataItem(sysDataItemQuery);
        } catch (Exception e) {
            logger.error("获取字典值失败", e);
        }
        return list;
    }

    @Override
    public int updateDataItem(SysDataItem sysDataItem) throws Exception {
        return 0;
    }

    @Override
    public int delDataItem(SysDataItem sysDataItem) throws Exception {
        int count = 0;
        try {
            count = dim.delDataItem(sysDataItem);
        } catch (Exception e) {
            logger.error("删除字典值失败", e);
        }
        return count;
    }

    @Override
    public int addDataItem(SysDataItem sysDataItem) throws Exception {
        return 0;
    }

    @Override
    public int getDataCountItem(SysDataItemQuery sysDataItemQuery) throws Exception {
        int count = 0;

        try {
            count = dim.getDataItemCount(sysDataItemQuery);
        } catch (Exception e) {
            logger.error("获取字典值失败", e);
        }
        return count;
    }


}
