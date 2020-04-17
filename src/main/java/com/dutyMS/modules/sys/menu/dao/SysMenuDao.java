package com.dutyMS.modules.sys.menu.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.menu.bo.SysMenuSimpleBo;
import com.dutyMS.modules.sys.menu.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 菜单管理
 *
 * @author chenshun
 */
@Mapper
@Component
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuSimpleBo> queryNotButtonList();

    /**
     * 获取菜单列表
     */
    List<SysMenuSimpleBo> listSimpleBo(Long operatorId);

    /**
     * 根据名称查询实体对象
     */
    SysMenuEntity queryByName(@Param("name") String name, @Param("parentId") Long parentId);

    /**
     * 通过id软删除菜单
     * @param menuId 菜单id
     * @return 被修改的数据条数
     */
    Integer deleteByMenuId(long menuId);

}
