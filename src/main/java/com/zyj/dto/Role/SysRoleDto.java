package com.zyj.dto.Role;

import java.io.Serializable;
import java.util.List;

public class SysRoleDto implements Serializable {
    private List<Integer> menuIds;

    private Integer roleId;

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
