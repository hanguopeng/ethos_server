package com.dutyMS.modules.sheet.controller;

import com.dutyMS.common.utils.R;
import com.dutyMS.modules.sheet.bo.QuerySheetBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.entity.SheetEntity;
import com.dutyMS.modules.sheet.service.SheetProcessService;
import com.dutyMS.modules.sheet.service.SheetService;
import com.dutyMS.modules.sheet.vo.SheetProcessVo;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sheet/operation")
@Api(tags = "操作工单接口")
public class SheetController {
    @Resource
    private SheetService sheetService;
    @Resource
    private SheetProcessService sheetProcessService;

    @PostMapping("/createSheet")
    @ApiOperation("创建工单")
    public R createSheet(@RequestBody SheetInfoBo bo){
        Long sheetId = sheetService.createSheet(bo);
        return R.ok().result(sheetId);
    }
    @PostMapping("/sendSheet")
    @ApiOperation("工单派发")
    public R sendSheet(@RequestBody SheetInfoBo bo) {
        Long sheetId = sheetService.createSheet(bo);
        bo.setSheetId(sheetId);
        sheetProcessService.sendSheet(bo);
        return R.ok();
    }

    @PostMapping("/querySheetInfo")
    @ApiOperation("工单查询")
    public R querySheetInfo(@RequestBody QuerySheetBo bo) {
        return R.ok().result(sheetService.querySheetInfo(bo));
    }

    @GetMapping("/queryCitys")
    @ApiOperation("城市查询")
    public R queryCitys() {
        List<Map<String,String>> list = sheetService.queryCitys();
        JSONObject json = new JSONObject();
        json.put("cityList", list);
        return R.ok().result(list);
    }

    @GetMapping("/getSheetNo/{regionId}")
    @ApiOperation("获取工单编号")
    public R getSheetNo(@NotBlank @PathVariable("regionId")String regionId) {

        return R.ok().result(sheetService.getSheetNo(regionId));
    }

    @GetMapping("/orderTask/{sheetId}")
    @ApiOperation("接单接口")
    public R orderTask(@NotBlank @PathVariable("sheetId")Long sheetId) {
        Integer integer = sheetProcessService.orderTask(sheetId);
        if(integer==1){
            return R.ok().result("接单成功！");
        }else{
            return R.ok().result("接单失败");
        }
    }

    @GetMapping("/queryProcess/{sheetId}")
    @ApiOperation("获取工单流程")
    public R queryProcess(@NotBlank @PathVariable("sheetId")String sheetNo) {
        return R.ok().result(sheetProcessService.queryProcess(sheetNo));
    }

    @PostMapping("/resolveTask")
    @ApiOperation("处理反馈")
    public R resolveTask(@RequestBody SheetProcessVo vo){
        sheetProcessService.resolveTask(vo);
        return R.ok();
    }
}
