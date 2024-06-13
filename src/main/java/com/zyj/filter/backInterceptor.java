package com.zyj.filter;


import com.alibaba.fastjson.JSONObject;
import com.zyj.common.MyAnnotation;
import com.zyj.common.WebRedisJson;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.util.RedisUtil;
import com.zyj.util.Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class backInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler instanceof  HandlerMethod){
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
////            MyAnnotation  myAnnotation = handlerMethod.getMethodAnnotation(MyAnnotation.class);
//            String[] values = handlerMethod.getMethodAnnotation(MyAnnotation.class).value();
//        }


        String admin = Util.getCookie(request, "adminName");
        String path = request.getServletPath();


        if (path.equals("/html/back/login_manage/login.html") || path.contains("js")
                || path.contains("img") || path.contains("png") || path.contains("css")) {
            return true;
        } else {
            if (admin == null) {
//                request.getRequestDispatcher("/back/admin/toLogin");
                response.sendRedirect("/back/admin/toLogin");
                return false;
            }

//            WebRedisJson webRedisJson = (WebRedisJson) RedisUtil.get(admin);
//            List<SysMenu> menuList = webRedisJson.getSysMenu();
//            boolean flag = false;
//
//            for (SysMenu menu: menuList
//            ) {
//                if (Arrays.asList(values).contains(menu.getMenuCode())){
//                    flag = true;
//                    System.out.println(menu.getMenuCode());
//                    break;
//                }
//            }
//
//            if (flag == false){
////                response.setContentType("application/json;charset=utf-8");
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("code", -500);
//                jsonObject.put("message", "该方法权限未拥有");
//                response.getWriter().write(jsonObject.toString());
//                response.sendRedirect("/back/admin/toLogin");
//                return false;
//            }


            return true;

        }


    }
}
