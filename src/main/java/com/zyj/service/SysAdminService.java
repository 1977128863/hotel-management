package com.zyj.service;

import com.zyj.dto.Admin.SysAdmin;
import com.zyj.dto.Admin.SysAdminQuery;

import java.util.ArrayList;
import java.util.List;

public interface SysAdminService {
    public List<SysAdmin> getAdmin(SysAdminQuery sysAdminQuery, Integer level) throws Exception;

    public int addAdmin(SysAdmin sysAdmin) throws Exception;

    public int deleteAdmin(SysAdmin sysAdmin) throws Exception;

    public int updateAdmin(SysAdmin sysAdmin) throws Exception;

    public int batchDeleteAdmin(List<Integer> ids) throws Exception;

    int getAdminCount(SysAdminQuery sysAdminQuery, Integer level) throws Exception;

}
