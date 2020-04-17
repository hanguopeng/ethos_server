package com.dutyMS.modules.sys.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.exception.RRException;
import com.dutyMS.common.utils.Constant;
import com.dutyMS.modules.sys.role.dao.SysRoleDao;
import com.dutyMS.modules.sys.user.bo.SysUserDetailsBo;
import com.dutyMS.modules.sys.user.bo.SysUserSimpleBo;
import com.dutyMS.modules.sys.user.dao.SysUserDao;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import com.dutyMS.modules.sys.user.service.SysUserRoleService;
import com.dutyMS.modules.sys.user.service.SysUserService;
import com.dutyMS.modules.sys.user.vo.SysUserQueryVo;
import com.dutyMS.modules.sys.user.vo.SysUserVo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


/**
 * 系统登陆用户
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SysUserDao sysUserDao;
    private final SysRoleDao sysRoleDao;
    private final SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserServiceImpl(SysUserDao sysUserDao, SysRoleDao sysRoleDao, SysUserRoleService sysUserRoleService) {
        this.sysUserDao = sysUserDao;
        this.sysRoleDao = sysRoleDao;
        this.sysUserRoleService = sysUserRoleService;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }


    @Override
    public Integer count(SysUserQueryVo vo, Long operatorId) {
        //只有超级管理员，才能查看所有管理员列表
        if (operatorId != Constant.SUPER_ADMIN) {
            vo.setOperatorId(operatorId);
        }
        return sysUserDao.count(vo);
    }


    @Override
    public List<SysUserSimpleBo> list(SysUserQueryVo vo, Long operatorId) {
        //只有超级管理员，才能查看所有管理员列表
        if (operatorId != Constant.SUPER_ADMIN) {
            vo.setOperatorId(operatorId);
        }
        return sysUserDao.listSimpleBo(vo);
    }

    @Override
    public SysUserDetailsBo details(Long userId) {
        SysUserEntity entity = sysUserDao.selectById(userId);
        if (entity == null) {
            throw new RRException("无法查询出用户信息!");
        }
        SysUserDetailsBo bo = new SysUserDetailsBo();
        bo.setUserId(entity.getUserId());
        bo.setUsername(entity.getUsername());
        bo.setStatus(entity.getStatus());
        bo.setRoleIdList(sysUserRoleService.queryRoleIdList(userId));
        return bo;
    }

    @Override
    @Transactional
    public void save(SysUserVo vo, Long operatorId) {
        SysUserEntity entity = new SysUserEntity();
        if (sysUserDao.queryByUserName(vo.getUsername()) != null) {
            throw new RRException("以有相同名称的用户信息");
        }
        entity.setUsername(vo.getUsername());
        entity.setStatus(vo.getStatus());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        entity.setPassword(new Sha256Hash(vo.getPassword(), salt).toHex());
        entity.setSalt(salt);
        entity.setCreateUserId(operatorId);
        sysUserDao.insert(entity);

        //检查角色是否越权
        checkRole(vo.getRoleIdList(), operatorId);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(entity.getUserId(), vo.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(Long id, SysUserVo vo, Long operatorId) {
        SysUserEntity entity = sysUserDao.selectById(id);
        if (entity == null) {
            throw new RRException("无法查询出用户信息!");
        }
        SysUserEntity entityTemp = sysUserDao.queryByUserName(vo.getUsername());
        if (entityTemp != null && !entityTemp.getUserId().equals(id)) {
            throw new RRException("已有相同名称的用户信息");
        }
        entity.setUsername(vo.getUsername());
        entity.setStatus(vo.getStatus());
        if (StringUtils.isNotEmpty(vo.getPassword())) {
            vo.setPassword(new Sha256Hash(vo.getPassword(), entity.getSalt()).toHex());
        }
        sysUserDao.updateById(entity);

        //检查角色是否越权
        checkRole(vo.getRoleIdList(), operatorId);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(entity.getUserId(), vo.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.deleteBatchIds(Arrays.asList(userId));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new EntityWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(List<Long> roleIdList, Long operatorId) {
        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (operatorId == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdListTemp = sysRoleDao.queryRoleIdList(operatorId);

        //判断是否越权
        if (!roleIdListTemp.containsAll(roleIdList)) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }

}
