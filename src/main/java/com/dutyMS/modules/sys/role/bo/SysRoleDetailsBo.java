package com.dutyMS.modules.sys.role.bo;

import java.util.List;

public class SysRoleDetailsBo extends SysRoleSimpleBo {
    private List<Long> menuIdList;//菜单列表

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
