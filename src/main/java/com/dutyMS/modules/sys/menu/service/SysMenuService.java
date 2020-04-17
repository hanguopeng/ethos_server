package com.dutyMS.modules.sys.menu.service;


import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sys.menu.bo.SysMenuDetailsBo;
import com.dutyMS.modules.sys.menu.bo.SysMenuSimpleBo;
import com.dutyMS.modules.sys.menu.entity.SysMenuEntity;
import com.dutyMS.modules.sys.menu.vo.SysMenuVo;

import java.util.List;


/**
 * 菜单管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:16
 */
public interface SysMenuService extends IService<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuSimpleBo> queryNotButtonList();

	/**
	 * 获取用户菜单列表
	 */
	List<SysMenuEntity> getUserMenuList(Long userId);
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenuSimpleBo> list(Long operatorId);

	SysMenuDetailsBo details(Long menuId);

	void create(SysMenuVo vo,Long operatorId);

	void update(Long id,SysMenuVo vo,Long operatorId);

	/**
	 * 删除
	 */
	void delete(Long menuId);

	/**
	 * 通过id软删除菜单
	 * @param menuId 菜单id
	 */
	void deleteByMenuId(long menuId);
}
