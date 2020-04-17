package com.dutyMS.modules.sys.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sys.user.bo.SysUserDetailsBo;
import com.dutyMS.modules.sys.user.bo.SysUserSimpleBo;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import com.dutyMS.modules.sys.user.vo.SysUserQueryVo;
import com.dutyMS.modules.sys.user.vo.SysUserVo;

import java.util.List;


/**
 * 系统用户
 *
 * @author chenshun
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);
    Integer count(SysUserQueryVo vo, Long operatorId);
    List<SysUserSimpleBo> list(SysUserQueryVo vo, Long operatorId);

    SysUserDetailsBo details(Long userId);
    /**
     * 保存用户
     */
    void save(SysUserVo vo,Long operatorId);

    /**
     * 修改用户
     */
    void update(Long id,SysUserVo vo,Long operatorId);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);


}
