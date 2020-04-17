package com.dutyMS.modules.sys.role.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.role.bo.SysRoleSimpleBo;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import com.dutyMS.modules.sys.role.vo.SysRoleQueryVo;
import com.dutyMS.modules.sys.user.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:33
 */
@Mapper
@Component
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

	Integer count(SysRoleQueryVo vo);

	List<SysRoleSimpleBo>  listSimpleBo(SysRoleQueryVo vo);
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysRoleEntity queryByRoleName(String roleName);

	List<SysRoleEntity> querySendObj();

	List<SysUserEntity> queryRoleById(@Param("roleId") String roleId);
}
