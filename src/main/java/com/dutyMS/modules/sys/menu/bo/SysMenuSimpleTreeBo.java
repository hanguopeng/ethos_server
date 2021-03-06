package com.dutyMS.modules.sys.menu.bo;

import java.util.List;

public class SysMenuSimpleTreeBo {
    private Long menuId;//菜单ID
    private Long parentId;//父菜单ID，一级菜单为0
    private String parentName;//父菜单名称
    private String name;//菜单名称
    private String url;//菜单URL
    private String perms;//授权(多个用逗号分隔，如：user:list,user:create)
    private Integer type;//类型     0：目录   1：菜单   2：按钮
    private String icon;//菜单图标
    private Integer orderNum;//排序
    private Boolean open;//ztree属性
    private List<SysMenuSimpleTreeBo> list;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<SysMenuSimpleTreeBo> getList() {
        return list;
    }

    public void setList(List<SysMenuSimpleTreeBo> list) {
        this.list = list;
    }
}
