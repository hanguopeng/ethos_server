package com.dutyMS.modules.sys.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import com.dutyMS.modules.sys.user.bo.SysUserSimpleBo;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import com.dutyMS.modules.sys.user.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统用户
 *
 * @author chenshun
 */
@Mapper
@Component
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	Integer count(SysUserQueryVo vo);

	List<SysUserSimpleBo>  listSimpleBo(SysUserQueryVo vo);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	/**
	 * 根据用户ID，查询系统用户
	 */
	SysUserEntity queryByUserId(Long userId);

	SysRoleEntity queryRoleByUserId(Long userId);

}
