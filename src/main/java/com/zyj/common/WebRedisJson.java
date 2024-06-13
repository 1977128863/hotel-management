package com.zyj.common;

import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Menu.SysMenu;
import com.zyj.dto.Role.SysRole;

import java.io.Serializable;
import java.util.List;

public class WebRedisJson implements Serializable {

    //用户信息
    private SysAdmin sysAdmin;

    //菜单信息
    private List<SysMenu> sysMenu;

    //角色信息
    private SysRole sysRole;

    //页面路径
    private List<String> sysPowerHtml;

    public WebRedisJson() {
    }

    public WebRedisJson(SysAdmin sysAdmin, List<SysMenu> sysMenu, SysRole sysRole, List<String> sysPowerHtml) {
        this.sysAdmin = sysAdmin;
        this.sysMenu = sysMenu;
        this.sysRole = sysRole;
        this.sysPowerHtml = sysPowerHtml;
    }

    public SysAdmin getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(SysAdmin sysAdmin) {
        this.sysAdmin = sysAdmin;
    }

    public List<SysMenu> getSysMenu() {
        return sysMenu;
    }

    public void setSysMenu(List<SysMenu> sysMenu) {
        this.sysMenu = sysMenu;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<String> getSysPowerHtml() {
        return sysPowerHtml;
    }

    public void setSysPowerHtml(List<String> sysPowerHtml) {
        this.sysPowerHtml = sysPowerHtml;
    }

    @Override
    public String toString() {
        return "WebRedisJson{" +
                "sysAdmin=" + sysAdmin +
                ", sysMenu=" + sysMenu +
                ", sysRole=" + sysRole +
                ", sysPowerHtml=" + sysPowerHtml +
                '}';
    }
}
