package com.zyj.dao;


import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.PowerHtml.SysPowerHtml;
import com.zyj.dto.PowerHtml.SysPowerHtmlQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPowerHtmlMapper {
    int deleteByPrimaryKey(Integer htmlId);

    int insert(SysPowerHtml record);

    int insertSelective(SysPowerHtml record);

    SysPowerHtml selectByPrimaryKey(Integer htmlId);

    int updateByPrimaryKeySelective(SysPowerHtml record);

    int updateByPrimaryKey(SysPowerHtml record);

    List<String> getPowerHtmlByMenus(List<SysMenu> list);

    int insertPowerHtml(@Param("list") List<String> list);

    List<SysPowerHtml> selectHtml(SysPowerHtmlQuery sysPowerHtmlQuery);

    int selectHtmlCount(SysPowerHtmlQuery sysPowerHtmlQuery);

    List<String> getAllHtml();

    int updateHtmlByMenuIdAndHref(@Param("menuId") Integer menuId, @Param("htmlHref") String htmlHref);
}