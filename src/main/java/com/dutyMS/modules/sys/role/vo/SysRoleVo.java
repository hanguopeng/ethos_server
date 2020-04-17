package com.dutyMS.modules.sys.role.vo;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class SysRoleVo {
    @NotBlank(message="角色名称不能为空")
    private String roleName;//角色名称
    private String remark;//备注
    private List<Long> menuIdList;//菜单列表

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
