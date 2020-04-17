package com.dutyMS.modules.sys.user.bo;


import java.util.List;

public class SysUserDetailsBo extends SysUserSimpleBo{
    private List<Long> roleIdList;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
