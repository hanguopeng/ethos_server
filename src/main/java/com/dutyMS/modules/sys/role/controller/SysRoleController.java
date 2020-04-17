package com.dutyMS.modules.sys.role.controller;

import com.dutyMS.common.annotation.SysLog;
import com.dutyMS.common.utils.Constant;
import com.dutyMS.common.utils.PageJsonUtils;
import com.dutyMS.common.utils.R;
import com.dutyMS.common.validator.ValidatorUtils;
import com.dutyMS.modules.sys.role.entity.SysRoleEntity;
import com.dutyMS.modules.sys.role.service.SysRoleService;
import com.dutyMS.modules.sys.role.vo.SysRoleQueryVo;
import com.dutyMS.modules.sys.role.vo.SysRoleVo;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author chenshun
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	private final SysRoleService sysRoleService;

	@Autowired
	public SysRoleController(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<SysRoleEntity> list = sysRoleService.selectByMap(map);
		return R.ok().put("list", list);
	}

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(SysRoleQueryVo vo){
		Integer totalCount= sysRoleService.count(vo,getUserId());
		if(totalCount!=0){
			return R.ok().result(PageJsonUtils.create(
					sysRoleService.list(vo,getUserId()),
					totalCount,
					vo.getLimit(),
					vo.getPage()));
		}else{
			return R.ok().result(PageJsonUtils.create(
					null,
					totalCount,
					vo.getLimit(),
					vo.getPage()));
		}
	}

	/**
	 * 角色信息
	 */
	@GetMapping("/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		return R.ok().result(sysRoleService.details(roleId));
	}

	@GetMapping("/querySendObj")
	@ApiOperation("获取派发对象角色组")
	public R querySendObj() {
		return R.ok().result(sysRoleService.querySendObj());
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping()
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleVo vo){
		ValidatorUtils.validateEntity(vo);
		sysRoleService.save(vo,this.getUserId());
		return R.ok();
	}

	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/{id}")
	@RequiresPermissions("sys:role:update")
	public R update(@PathVariable("id") Long id,@RequestBody SysRoleVo vo){
		ValidatorUtils.validateEntity(vo);
		sysRoleService.update(id,vo,this.getUserId());
		return R.ok();
	}

	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);

		return R.ok();
	}
}
