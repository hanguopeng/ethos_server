package com.dutyMS.modules.sys.menu.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.exception.RRException;
import com.dutyMS.common.utils.Constant;
import com.dutyMS.common.utils.MapUtils;
import com.dutyMS.modules.sys.menu.bo.SysMenuDetailsBo;
import com.dutyMS.modules.sys.menu.bo.SysMenuSimpleBo;
import com.dutyMS.modules.sys.menu.dao.SysMenuDao;
import com.dutyMS.modules.sys.menu.entity.SysMenuEntity;
import com.dutyMS.modules.sys.menu.service.SysMenuService;
import com.dutyMS.modules.sys.menu.vo.SysMenuVo;
import com.dutyMS.modules.sys.role.service.SysRoleMenuService;
import com.dutyMS.modules.sys.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenuSimpleBo> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public List<SysMenuSimpleBo> list(Long operatorId) {
        return sysMenuDao.listSimpleBo(operatorId);
    }

    @Override
    public SysMenuDetailsBo details(Long menuId) {
        SysMenuEntity entity = sysMenuDao.selectById(menuId);
        if (entity == null) {
            throw new RRException("无法查询到相关菜单");
        }
        SysMenuDetailsBo bo = new SysMenuDetailsBo();
        bo.setMenuId(entity.getMenuId());
        bo.setParentId(entity.getParentId());
        bo.setName(entity.getName());
        bo.setUrl(entity.getUrl());
        bo.setPerms(entity.getPerms());
        bo.setType(entity.getType());
        bo.setIcon(entity.getIcon());
        bo.setOrderNum(entity.getOrderNum());
        return bo;
    }

    @Override
    public void create(SysMenuVo vo, Long operatorId) {
        if (sysMenuDao.queryByName(vo.getName(), vo.getParentId()) != null) {
            throw new RRException("以有相同名称的菜单信息");
        }
        SysMenuEntity entity = new SysMenuEntity();
        entity.setParentId(vo.getParentId());
        entity.setName(vo.getName());
        entity.setUrl(vo.getUrl());
        entity.setPerms(vo.getPerms());
        entity.setType(vo.getType());
        entity.setIcon(vo.getIcon());
        entity.setOrderNum(vo.getOrderNum());
        sysMenuDao.insert(entity);
    }

    @Override
    public void update(Long id, SysMenuVo vo, Long operatorId) {
        SysMenuEntity entity = sysMenuDao.selectById(id);
        if (entity == null) {
            throw new RRException("无法查询到相关菜单");
        }
        SysMenuEntity ee = sysMenuDao.queryByName(vo.getName(), vo.getParentId());
        if (ee != null && !entity.getMenuId().equals(ee.getMenuId())) {
            throw new RRException("以有相同名称的菜单信息");
        }
        entity.setParentId(vo.getParentId());
        if(vo.getParentId()==0){
            entity.setParentName("");
        }else{
            SysMenuEntity parentEntity = sysMenuDao.selectById(vo.getParentId());
            if (parentEntity == null) {
                throw new RRException("上级结点无法查询到相关菜单");
            }
            entity.setParentName(parentEntity.getName());
        }
        entity.setName(vo.getName());
        entity.setUrl(vo.getUrl());
        entity.setPerms(vo.getPerms());
        entity.setType(vo.getType());
        entity.setIcon(vo.getIcon());
        entity.setOrderNum(vo.getOrderNum());
        sysMenuDao.updateById(entity);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }

    @Override
    public void deleteByMenuId(long menuId) {
        sysMenuDao.deleteByMenuId(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
        for (SysMenuEntity entity : menuList) {
            //目录
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
