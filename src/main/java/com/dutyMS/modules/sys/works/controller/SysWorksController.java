package com.dutyMS.modules.sys.works.controller;

import com.dutyMS.common.annotation.SysLog;
import com.dutyMS.common.utils.PageJsonUtils;
import com.dutyMS.common.utils.R;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import com.dutyMS.modules.sys.works.service.SysWorksService;
import com.dutyMS.modules.sys.works.vo.SysWorksVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统-任务项管理控制层
 * @author Mela.S
 * @date 2020/3/17
 */
@RestController
@RequestMapping("/sys/works")
@Api(tags = "系统-任务项管理控制层")
public class SysWorksController extends AbstractController {
    @Resource
    private SysWorksService sysWorksService;

    /**
     * 查询在用所有任务项
     */
    @GetMapping("/list")
    @ApiOperation("查询在用所有任务项")
    public R list(SysWorksVo vo) {
        Integer totalCount= sysWorksService.countWorksListByVo(vo);
        if(totalCount!=0){
            return R.ok().result(PageJsonUtils.create(
                    sysWorksService.getWorksListByVo(vo),
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
     * 根据ID主键查询任务项
     */
    @GetMapping("/info/{workId}")
    @ApiOperation("根据ID主键查询任务项")
    public R info(@PathVariable("workId") String workId) {
        return R.ok().result(sysWorksService.getWorkInfoById(workId));
    }

    /**
     * 保存
     */
    @SysLog("保存任务项")
    @PostMapping("/save")
    @ApiOperation("保存任务项")
    public R save(@RequestBody SysWorksVo vo) {
        vo.setLoginUser(getUserId());
        Integer result = sysWorksService.saveEntity(vo);
        if (result==1){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 修改任务项
     */
    @SysLog("修改任务项")
    @PostMapping("/update")
    @ApiOperation("修改任务项")
    public R update(@RequestBody SysWorksVo vo) {
        vo.setLoginUser(getUserId());
        Integer result = sysWorksService.updateEntity(vo);
        if (result==1){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 删除任务项
     */
    @SysLog("删除任务项")
    @PostMapping("/delete/{workId}")
    @ApiOperation("删除任务项")
    public R delete(@PathVariable("workId") String workId) {
        Integer result = sysWorksService.deleteInfoById(workId);
        if (result==1){
            return R.ok();
        }else{
            return R.error();
        }
    }

}
