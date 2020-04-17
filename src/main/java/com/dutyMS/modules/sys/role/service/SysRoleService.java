package com.dutyMS.modules.sys.role.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.common.utils.PageUtils;
import com.dutyMS.modules.sys.role.bo.SysRoleDetailsBo;
import com.dutyMS.modules.sys.role.bo.SysRoleSimpleBo;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import com.dutyMS.modules.sys.role.vo.SysRoleQueryVo;
import com.dutyMS.modules.sys.role.vo.SysRoleVo;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author chenshun
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	Integer count(SysRoleQueryVo vo, Long operatorId);

	List<SysRoleSimpleBo> list(SysRoleQueryVo vo, Long operatorId);

	SysRoleDetailsBo details(Long roleId);

	void save(SysRoleVo vo, Long operatorId);

	void update(Long id,SysRoleVo vo,Long operatorId);

	void deleteBatch(Long[] roleIds);

	List<Map<Object, Object>> querySendObj();


	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
