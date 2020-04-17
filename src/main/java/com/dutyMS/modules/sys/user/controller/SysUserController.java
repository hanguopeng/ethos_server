package com.dutyMS.modules.sys.user.controller;

import com.dutyMS.common.annotation.SysLog;
import com.dutyMS.common.utils.PageJsonUtils;
import com.dutyMS.common.utils.R;
import com.dutyMS.common.validator.Assert;
import com.dutyMS.common.validator.ValidatorUtils;
import com.dutyMS.common.validator.group.AddGroup;
import com.dutyMS.common.validator.group.UpdateGroup;
import com.dutyMS.modules.sys.user.service.SysUserService;
import com.dutyMS.modules.sys.user.vo.PasswordForm;
import com.dutyMS.modules.sys.user.vo.SysUserQueryVo;
import com.dutyMS.modules.sys.user.vo.SysUserVo;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户
 *
 * @author chenshun
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "系统用户接口")
public class SysUserController extends AbstractController {
    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    @ApiOperation("修改密码")
    public R password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");
        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error("原密码不正确");
        }
        return R.ok();
    }

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    @ApiOperation("所有用户列表")
    public R list(SysUserQueryVo vo) {
        Integer totalCount = sysUserService.count(vo, getUserId());
        if (totalCount != 0) {
            return R.ok().result(PageJsonUtils.create(
                    sysUserService.list(vo, getUserId()),
                    totalCount,
                    vo.getLimit(),
                    vo.getPage()));
        } else {
            return R.ok().result(PageJsonUtils.create(
                    null,
                    totalCount,
                    vo.getLimit(),
                    vo.getPage()));
        }
    }

    /**
     * 用户信息
     */
    @GetMapping("/{userId}")
    @RequiresPermissions("sys:user:info")
    @ApiOperation("用户信息")
    public R info(@PathVariable("userId") Long userId) {
        return R.ok().result(sysUserService.details(userId));
    }

    /**
     * 保存用户
     * 当前系统的用户由同步得出，前端不提供主动增加入口
     * todo 增加用户同步接口
     */
    @SysLog("保存用户")
    @PostMapping()
    @RequiresPermissions("sys:user:save")
    @ApiOperation("保存用户")
    public R save(@RequestBody SysUserVo vo) {
        ValidatorUtils.validateEntity(vo, AddGroup.class);
        sysUserService.save(vo, this.getUserId());
        return R.ok();
    }

    /**
     * 修改用户
     * 当前系统的用户由同步得出，前端不提供主动修改入口
     */
    @SysLog("修改用户")
    @PostMapping("/{id}")
    @RequiresPermissions("sys:user:update")
    @ApiOperation("修改用户")
    public R update(@PathVariable("id") Long id, @RequestBody SysUserVo vo) {
        ValidatorUtils.validateEntity(vo, UpdateGroup.class);
        sysUserService.update(id, vo, this.getUserId());
        return R.ok();
    }

    /**
     * 删除用户，当前删除方法为硬删
     * 当前系统的用户由同步得出，前端不提供主动删除入口
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    @ApiOperation("删除用户")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return R.ok();
    }
}
