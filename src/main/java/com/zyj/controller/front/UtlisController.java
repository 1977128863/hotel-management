package com.zyj.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class UtlisController {
    @RequestMapping("/restaurant")
    public String restaurant() {
        return "front/restaurant";
    }

    @RequestMapping("/index")
    public String index() {
        return "front/index";
    }

    @RequestMapping("/liuing")
    public String liuing() {
        return "front/liuing";
    }

    @RequestMapping("/liuroot")
    public String liuroot() {
        return "front/liuroot";
    }

    @RequestMapping("/wuroot")
    public String wuroot() {
        return "front/wuroot";
    }

    @RequestMapping("/wuing")
    public String wuing() {
        return "front/wuing";
    }

    @RequestMapping("/siing")
    public String siing() {
        return "front/siing";
    }

    @RequestMapping("/siroot")
    public String siroot() {
        return "front/siroot";
    }

    @RequestMapping("/sanroot")
    public String sanroot() {
        return "front/sanroot";
    }

    @RequestMapping("/saning")
    public String saning() {
        return "front/saning";
    }

    @RequestMapping("/towing")
    public String towing() {
        return "front/towing";
    }

    @RequestMapping("/towroot")
    public String towroot() {
        return "front/towroot";
    }

    @RequestMapping("/oneing")
    public String oneing() {
        return "front/oneing";
    }

    @RequestMapping("/oneroot")
    public String oneroot() {
        return "front/oneroot";
    }

    @RequestMapping("/login")
    public String login() {
        return "front/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "front/zhuce";
    }
}
