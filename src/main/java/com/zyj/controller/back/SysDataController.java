package com.zyj.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.zyj.common.DataJson;
import com.zyj.dto.Data.SysData;
import com.zyj.dto.Data.SysDataQuery;
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
@RequestMapping("/back/data")
public class SysDataController {
    private static Logger logger = LogManager.getLogger(SysDataController.class);

    @Autowired
    private SysDataService ds;


    @PostMapping("/getDataForApply")
    @ResponseBody
    public JSONObject getDataForApply(String key, int value) {
        List<String> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {
            list = ds.getDataForApply(key, value);
            if (list == null) {
                jsonObject.put("code", 300);
                jsonObject.put("message", "渲染失败");
                return jsonObject;
            }
        } catch (Exception e) {
            logger.error("字典值获取失败", e);
        }

        jsonObject.put("code", 200);
        jsonObject.put("message", "字典值获取成功");
        jsonObject.put("data", list);
        return jsonObject;
    }

    @PostMapping("/getData")
    @ResponseBody
    public DataJson<SysData> getData(SysDataQuery sysDataQuery) {
        List<SysData> list = new ArrayList<SysData>();
        int count = 0;
        sysDataQuery.setIsDelete(0);
        try {
            if (sysDataQuery.getLimit() != null && sysDataQuery.getPage() != null) {
                int start = sysDataQuery.getLimit() * (sysDataQuery.getPage() - 1);
                sysDataQuery.setStart(start);
            }
            list = ds.getData(sysDataQuery);
            count = ds.getDataCount(sysDataQuery);
            if (count == 0 && list.size() == 0) {
                return new DataJson<>(0, "暂无数据");
            } else if (list == null || count == 0) {
                return new DataJson<>(-1, "获取失败");
            }
        } catch (Exception e) {
            logger.error("字典获取失败", e);
        }


        return new DataJson<>(0, "获取成功", list, count);
    }

    @PostMapping("/addData")
    @ResponseBody
    public DataJson addData(SysData sysData) {
        sysData.setIsDelete(0);
        int count = 0;
        try {
            count = ds.addData(sysData);
            if (count == 0) {
                return new DataJson(300, "新增失败");
            } else if (count == -1) {
                return new DataJson(500, "系统错误");
            }
        } catch (Exception e) {
            logger.error("新增字典出错", e);
        }

        return new DataJson(200, "新增成功");
    }

    @PostMapping("/editData")
    @ResponseBody
    public DataJson editData(SysData sysData) {
        int count = 0;
        try {
            count = ds.updateData(sysData);
            if (count == 0) {
                return new DataJson(300, "修改失败");
            } else if (count == -1) {
                return new DataJson(500, "系统错误");
            }
        } catch (Exception e) {
            logger.error("修改字典出错", e);
        }

        return new DataJson(200, "修改成功");
    }

    @PostMapping("/delData")
    @ResponseBody
    public DataJson delData(SysData sysData) {
        int count = 0;
        sysData.setIsDelete(1);
        try {
            count = ds.delData(sysData);
            if (count == 0) {
                return new DataJson(300, "删除失败");
            } else if (count == -1) {
                return new DataJson(500, "系统错误");
            }
        } catch (Exception e) {
            logger.error("删除字典出错", e);
        }

        return new DataJson(200, "删除成功");
    }
}
