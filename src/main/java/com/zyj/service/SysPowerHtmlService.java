package com.zyj.service;

import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.PowerHtml.SysPowerHtml;
import com.zyj.dto.PowerHtml.SysPowerHtmlQuery;

import java.util.List;

public interface SysPowerHtmlService {
    public List<String> getPowerHtmlByMenus(List<SysMenu> list) throws Exception;

    public int insertHtml(List<String> htmlPathList);

    public List<SysPowerHtml> selectHtml(SysPowerHtmlQuery sysPowerHtmlQuery);

    public int selectHtmlCount(SysPowerHtmlQuery sysPowerHtmlQuery);

    public int updateHtmlAndMenu(String htmlHref, Integer menuId);
}
