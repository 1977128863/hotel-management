package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;
import com.zyj.dto.DataItem.SysDataItem;
import com.zyj.dto.DataItem.SysDataItemQuery;
import com.zyj.service.SysDataItemService;
import com.zyj.service.SysDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/back/dataItem")
public class SysDataItemController {
    private static Logger logger = LogManager.getLogger(SysDataItemController.class);

    @Autowired
    private SysDataItemService dis;

    @PostMapping("/getDataItem")
    @ResponseBody
    public DataJson<SysDataItem> getDataItem(SysDataItemQuery sysDataItemQuery) {
        List<SysDataItem> list = new ArrayList<SysDataItem>();
        int count = 0;
        sysDataItemQuery.setIsDelete(0);
        try {
            if (sysDataItemQuery.getLimit() != null && sysDataItemQuery.getPage() != null) {
                int start = sysDataItemQuery.getLimit() * (sysDataItemQuery.getPage() - 1);
                sysDataItemQuery.setStart(start);
            }
            list = dis.getDataItem(sysDataItemQuery);
            count = dis.getDataCountItem(sysDataItemQuery);
            if (count == 0 && list.size() == 0) {
                return new DataJson<>(0, "暂无数据");
            } else if (list == null || count == 0) {
                return new DataJson<>(-1, "获取失败");
            }
        } catch (Exception e) {
            logger.error("字典值获取失败", e);
        }


        return new DataJson<>(0, "获取成功", list, count);
    }

}
