package com.zyj.service.impl;

import com.zyj.dao.SysDataItemMapper;
import com.zyj.dao.SysDataMapper;
import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;
import com.zyj.dto.DataItem.SysDataItem;
import com.zyj.dto.DataItem.SysDataItemQuery;
import com.zyj.service.SysDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDataServiceImpl implements SysDataService {

    private static Logger logger = LogManager.getLogger(SysDataServiceImpl.class);

    @Autowired
    private SysDataMapper dm;

    @Autowired
    private SysDataItemMapper dim;

    @Override
    @Transactional
    public List<String> getDataForApply(String key, int value) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            list = dm.getDataForApply(key, value);
        } catch (Exception e) {
            logger.error("字典值获取失败", e);
        }


        return list;
    }

    @Override
    public List<SysData> getData(SysDataQuery sysDataQuery) throws Exception {
        List<SysData> list = new ArrayList<SysData>();

        try {
            list = dm.getData(sysDataQuery);
        } catch (Exception e) {
            logger.error("获取字典失败", e);
        }
        return list;
    }

    @Override
    public int updateData(SysData sysData) throws Exception {
        int count = 0;

        try {
            count = dm.updateByPrimaryKeySelective(sysData);
        } catch (Exception e) {
            logger.error("修改字典错误", e);
        }

        return count;
    }

    @Override
    public int delData(SysData sysData) throws Exception {
        int count = 0;
        SysDataItemQuery sysDataItemQuery = new SysDataItemQuery();
        sysDataItemQuery.setDataId(sysData.getDataId());
        count = dim.getDataItemCount(sysDataItemQuery);
        try {
            if (count > 0) {
                SysDataItem sysDataItem = new SysDataItem();
                sysDataItem.setIsDelete(1);
                sysDataItem.setDataId(sysData.getDataId());
                //空指针
                count = dim.delDataItem(sysDataItem);


            }
            count = dm.updateByPrimaryKeySelective(sysData);


        } catch (Exception e) {
            count = -1;
            logger.error("删除字典错误", e);
        }

        return count;
    }

    @Override
    public int addData(SysData sysData) throws Exception {
        int count = 0;

        try {
            count = dm.insertSelective(sysData);
        } catch (Exception e) {
            logger.error("新增字典错误", e);
        }

        return count;
    }

    @Override
    public int getDataCount(SysDataQuery sysDataQuery) throws Exception {
        int count = 0;

        try {
            count = dm.getDataCount(sysDataQuery);
        } catch (Exception e) {
            logger.error("获取字典失败", e);
        }
        return count;
    }
}
