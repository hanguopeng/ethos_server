package com.dutyMS.modules.sys.role.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.exception.RRException;
import com.dutyMS.common.utils.Constant;
import com.dutyMS.modules.sys.role.bo.SysRoleDetailsBo;
import com.dutyMS.modules.sys.role.bo.SysRoleSimpleBo;
import com.dutyMS.modules.sys.role.dao.SysRoleDao;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import com.dutyMS.modules.sys.role.service.SysRoleMenuService;
import com.dutyMS.modules.sys.role.service.SysRoleService;
import com.dutyMS.modules.sys.role.vo.SysRoleQueryVo;
import com.dutyMS.modules.sys.role.vo.SysRoleVo;
import com.dutyMS.modules.sys.user.dao.SysUserDao;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import com.dutyMS.modules.sys.user.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 角色
 *
 * @author chenshun
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SysRoleDao sysRoleDao;
    private final SysUserDao sysUserDao;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysUserRoleService sysUserRoleService;

    @Autowired
    public SysRoleServiceImpl(SysRoleDao sysRoleDao, SysUserDao sysUserDao, SysRoleMenuService sysRoleMenuService, SysUserRoleService sysUserRoleService) {
        this.sysRoleDao = sysRoleDao;
        this.sysUserDao = sysUserDao;
        this.sysRoleMenuService = sysRoleMenuService;
        this.sysUserRoleService = sysUserRoleService;
    }

    @Override
    public Integer count(SysRoleQueryVo vo, Long operatorId) {
        //只有超级管理员，才能查看所有管理员列表
        if (operatorId != Constant.SUPER_ADMIN) {
            vo.setOperatorId(operatorId);
        }
        return sysRoleDao.count(vo);
    }

    @Override
    public List<SysRoleSimpleBo> list(SysRoleQueryVo vo, Long operatorId) {
        //只有超级管理员，才能查看所有管理员列表
        if (operatorId != Constant.SUPER_ADMIN) {
            vo.setOperatorId(operatorId);
        }
        return sysRoleDao.listSimpleBo(vo);
    }

    @Override
    public SysRoleDetailsBo details(Long roleId) {
        SysRoleEntity entity = sysRoleDao.selectById(roleId);
        if (entity == null) {
            throw new RRException("无法查询出角色信息!");
        }
        SysRoleDetailsBo bo = new SysRoleDetailsBo();
        bo.setRoleId(entity.getRoleId());
        bo.setRoleName(entity.getRoleName());
        bo.setRemark(entity.getRemark());
        bo.setCreateUserId(entity.getCreateUserId());
        if (entity.getCreateTime() != null) {
            bo.setCreateTime(dateFormat.format(entity.getCreateTime()));
        }
        bo.setMenuIdList(sysRoleMenuService.queryMenuIdList(roleId));
        return bo;
    }

    @Override
    @Transactional
    public void save(SysRoleVo vo, Long operatorId) {
        SysRoleEntity entity = new SysRoleEntity();
        if (sysRoleDao.queryByRoleName(vo.getRoleName()) != null) {
            throw new RRException("以有相同名称的角色信息");
        }
        entity.setRoleName(vo.getRoleName());
        entity.setRemark(vo.getRemark());
        entity.setCreateTime(new Timestamp(new Date().getTime()));
        entity.setCreateUserId(operatorId);
        sysRoleDao.insert(entity);

        //检查角色是否越权
        checkPrems(vo.getMenuIdList(), operatorId);

        //保存用户与角色关系
        sysRoleMenuService.saveOrUpdate(entity.getRoleId(), vo.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(Long id, SysRoleVo vo, Long operatorId) {
        SysRoleEntity entity = sysRoleDao.selectById(id);
        if (entity == null) {
            throw new RRException("无法查询出角色信息!");
        }
        SysRoleEntity entityTemp = sysRoleDao.queryByRoleName(vo.getRoleName());
        if (entityTemp != null && !entityTemp.getRoleId().equals(id)) {
            throw new RRException("以有相同名称的角色信息");
        }
        entity.setRoleName(vo.getRoleName());
        entity.setRemark(vo.getRemark());
        sysRoleDao.updateById(entity);

        //检查角色是否越权
        checkPrems(vo.getMenuIdList(), operatorId);

        //保存用户与角色关系
        sysRoleMenuService.saveOrUpdate(entity.getRoleId(), vo.getMenuIdList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Map<Object, Object>> querySendObj() {
        List<SysRoleEntity> roleList = sysRoleDao.querySendObj();
        List<Map<Object,Object>> objList = new ArrayList<>();
        for(SysRoleEntity entity:roleList) {
            Map<Object,Object> map = new HashMap<>();
            map.put("value",entity.getRoleId().toString());
            map.put("label",entity.getRoleName());
            List<SysUserEntity> userList = sysRoleDao.queryRoleById(entity.getRoleId().toString());
            if(userList!=null&&userList.size()>0) {
                List<Map<Object,Object>> mapList = new ArrayList<>() ;
                for(SysUserEntity sysEntity:userList) {
                    Map<Object,Object> userMap = new HashMap<>();
                    userMap.put("value", sysEntity.getUserId());
                    userMap.put("label", sysEntity.getRealName());
                    mapList.add(userMap);
                }
                map.put("children",mapList);
            }
            objList.add(map);
        }
        return objList;
    }


    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(List<Long> menuIdList, Long operatorId) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if (operatorId == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdListTemp = sysUserDao.queryAllMenuId(operatorId);

        //判断是否越权
        if (!menuIdListTemp.containsAll(menuIdList)) {
            throw new RRException("新增角色的权限，已超出你的权限范围");
        }
    }
}
