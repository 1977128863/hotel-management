package com.zyj.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class getPowerHtml {

    public static List<String> list = new ArrayList<String>();

    /**
     * 获取某个路径下的所有html文件
     *
     * @param filePath
     * @return
     */
    public static List<String> gethtml(String filePath) {
        String path = "";

        File[] allFiles = new File(filePath).listFiles();
        for (int i = 0; i < allFiles.length; i++) {
            File file = allFiles[i];


            if (file.isFile()) {
                int index = file.getAbsolutePath().lastIndexOf(".");
                String lastStr = file.getAbsolutePath().substring(index, file.getAbsolutePath().length());
                if (lastStr.equals(".html")) {
                    Integer htmlStr = filePath.lastIndexOf("html");
                    path = file.getAbsolutePath().substring(htmlStr, file.getAbsolutePath().length());
                    list.add(path.replaceAll("\\\\", "/"));
                }

            } else {
                gethtml(file.getAbsolutePath());
            }
        }


        return list;
    }

//    public static void main(String[] args) {
//        gethtml("D:\\IDEA-workspace\\hotel-management\\src\\main\\webapp\\html");
//    }
}
