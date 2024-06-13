package com.zyj.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.StringValue;
import com.zyj.dto.File.SysFile;
import com.zyj.dto.File.SysFileQuery;
import com.zyj.service.SysFileService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;


@Controller
@RequestMapping("/back")
public class UploadController {
    @Autowired
    private SysFileService fs;

    private static Logger logger = LogManager.getLogger(UploadController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        JSONObject jsonObject = new JSONObject();

        long size = file.getSize();
        if (size > 1024 * 1024 * 5) {
            jsonObject.put("message", "图片大小不能超过5MB");
            jsonObject.put("code", "300");
            return jsonObject;
        }

        String path = "D://upload";
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();


        long time = new Date().getTime();
        String sub = fileName.substring(fileName.lastIndexOf("."));
        String newName = time + sub;


        File newFile = new File(file1, newName);
        file.transferTo(newFile);


        SysFile sysFile = new SysFile();
        sysFile.setFileCode(String.valueOf(time));
        sysFile.setFileName(newName);
        sysFile.setFileType(fileType);
        try {
            fs.addFile(sysFile);
        } catch (Exception e) {
            logger.error("图片路径存入路径出错", e);
        }

        jsonObject.put("message", "图片上传成功");
        jsonObject.put("code", "200");
        jsonObject.put("imgCode", time);
        jsonObject.put("fileName", newName);
        jsonObject.put("fileType", fileType);
        return jsonObject;
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(String fileName, String fileType, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setContentType(fileType);
        InputStream inputStream = new URL("http://127.0.0.1:8080/fileUpload/" + fileName).openStream();
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
    }

}
