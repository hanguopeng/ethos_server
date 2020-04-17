package com.dutyMS.modules.sys.menu.controller;

import com.dutyMS.common.annotation.SysLog;
import com.dutyMS.common.exception.RRException;
import com.dutyMS.common.utils.Constant;
import com.dutyMS.common.utils.R;
import com.dutyMS.common.validator.ValidatorUtils;
import com.dutyMS.modules.sys.menu.bo.SysMenuSimpleBo;
import com.dutyMS.modules.sys.menu.entity.SysMenuEntity;
import com.dutyMS.modules.sys.menu.service.SysMenuService;
import com.dutyMS.modules.sys.menu.vo.SysMenuVo;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import com.dutyMS.modules.sys.utils.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单
 *
 * @author chenshun
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    private final SysMenuService sysMenuService;
    private final ShiroService shiroService;

    @Autowired
    public SysMenuController(SysMenuService sysMenuService, ShiroService shiroService) {
        this.sysMenuService = sysMenuService;
        this.shiroService = shiroService;
    }

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public R nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public R list() {
        List<SysMenuSimpleBo> list = sysMenuService.list(this.getUserId());
        return R.ok().result(list);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public R select() {
        //查询列表数据
        List<SysMenuSimpleBo> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuSimpleBo root = new SysMenuSimpleBo();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId) {
        return R.ok().result(sysMenuService.details(menuId));
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @PostMapping()
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuVo vo) {
        //数据校验
        ValidatorUtils.validateEntity(vo);
        sysMenuService.create(vo,this.getUserId());
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @PostMapping("/{id}")
    @RequiresPermissions("sys:menu:update")
    public R update(@PathVariable("id") Long id, @RequestBody SysMenuVo vo) {
        //数据校验
        ValidatorUtils.validateEntity(vo);
        sysMenuService.update(id,vo,this.getUserId());
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 31) {
            return R.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return R.error("请先删除子菜单或按钮");
        }

        sysMenuService.deleteByMenuId(menuId);

        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
