package com.dutyMS.modules.sys.region.controller;

import com.dutyMS.common.utils.R;
import com.dutyMS.modules.sys.region.service.SysRegionService;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 地市管理控制层
 * @author Mela.S
 * @date 2019/12/20
 */
@RestController
@RequestMapping("/sys/region")
@Api(tags = "地市管理接口")
public class SysRegionController extends AbstractController {
    @Resource
    private SysRegionService sysRegionService;

    /**
     * 获取登陆人所在地市信息
     * 如果是登录人所在地市是省级，需要关联出子级地市信息
     */
    @GetMapping("/getLoginUserRegionInfo")
    @RequiresPermissions("duty:shiftGroup:manage")
    @ApiOperation("获取登陆人所在地市集合")
    public R getLoginUserRegionInfo() {
        return R.ok().result(sysRegionService.getRegionInfoByUserId(getUserId()));
    }


}
