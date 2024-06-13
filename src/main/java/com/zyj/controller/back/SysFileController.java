package com.zyj.controller.back;

import com.zyj.common.DataJson;
import com.zyj.dto.File.SysFile;
import com.zyj.dto.File.SysFileQuery;
import com.zyj.service.SysFileService;
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
@RequestMapping("/back/file")
public class SysFileController {
    private static Logger logger = LogManager.getLogger(SysFileController.class);

    @Autowired
    private SysFileService fs;

    @PostMapping("/getFile")
    @ResponseBody
    public DataJson<SysFile> getFile(SysFileQuery sysFileQuery) {
        List<SysFile> list = new ArrayList<SysFile>();

        try {
            list = fs.getFile(sysFileQuery);
            if (list == null) {
                return new DataJson<>(300, "图片文件获取失败");
            }
        } catch (Exception e) {
            logger.error("文件获取失败", e);
        }


        return new DataJson<>(200, "获取图片文件成功", list, list.size());
    }
}
