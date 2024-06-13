package com.zyj.service.impl;

import com.zyj.dao.SysPowerHtmlMapper;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.PowerHtml.SysPowerHtml;
import com.zyj.dto.PowerHtml.SysPowerHtmlQuery;
import com.zyj.service.SysPowerHtmlService;
import com.zyj.util.StreamListUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysPowerHtmlServiceImpl implements SysPowerHtmlService {
    private static Logger logger = LogManager.getLogger(SysPowerHtmlServiceImpl.class);

    @Autowired
    private SysPowerHtmlMapper phm;

    @Override
    @Transactional
    public List<String> getPowerHtmlByMenus(List<SysMenu> list) throws Exception {
        List<String> htmlList = new ArrayList<String>();

        try {
            htmlList = phm.getPowerHtmlByMenus(list);
        } catch (Exception e) {
            logger.error("html路径获取失败", e);
        }

        return htmlList;
    }

    @Override
    @Transactional
    public int insertHtml(List<String> htmlPathList) {
        int count = 0;
        List<List<String>> lists = new ArrayList<List<String>>();

        try {
            lists = StreamListUtil.forEachIterate(htmlPathList);
            List<String> allHtml = phm.getAllHtml();
            for (int i = 0; i < lists.size(); i++) {
                List<String> list = lists.get(i);
                list.removeAll(allHtml);
                if (list.size() > 0) {
                    count = phm.insertPowerHtml(list);
                    if (count < 0) {
                        count = -1;
                    }
                }

            }

        } catch (Exception e) {
            logger.error("html导入失败", e);
        }
        return count;
    }

    @Override
    @Transactional
    public List<SysPowerHtml> selectHtml(SysPowerHtmlQuery sysPowerHtmlQuery) {
        int count = 0;
        List<SysPowerHtml> list = new ArrayList<>();
        try {
            list = phm.selectHtml(sysPowerHtmlQuery);
        } catch (Exception e) {
            logger.error("获取html失败", e);
        }
        return list;
    }

    @Override
    @Transactional
    public int selectHtmlCount(SysPowerHtmlQuery sysPowerHtmlQuery) {
        int count = 0;
        try {
            count = phm.selectHtmlCount(sysPowerHtmlQuery);
        } catch (Exception e) {
            logger.error("获取html数量错误", e);
        }
        return count;
    }

    @Override
    @Transactional
    public int updateHtmlAndMenu(String htmlHref, Integer menuId) {
        int count = 0;
        try {
            count = phm.updateHtmlByMenuIdAndHref(menuId, htmlHref);
            if (count < 0) {
                return -1;
            }
        } catch (Exception e) {
            logger.error("修改页面归属菜单失败", e);
        }
        return count;
    }


}
