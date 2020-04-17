package com.dutyMS.modules.sys.user.vo;

import com.dutyMS.common.validator.group.AddGroup;
import com.dutyMS.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class SysUserVo {
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;//用户名
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    private String password;//密码
    private Integer status;//状态  0：禁用   1：正常
    private List<Long> roleIdList;//角色列表

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
