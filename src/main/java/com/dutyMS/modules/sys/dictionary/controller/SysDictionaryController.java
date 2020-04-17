package com.dutyMS.modules.sys.dictionary.controller;

import com.dutyMS.common.annotation.SysLog;
import com.dutyMS.common.utils.R;
import com.dutyMS.modules.sys.dictionary.bo.SysDictionaryBo;
import com.dutyMS.modules.sys.dictionary.entity.SysDictionaryEntity;
import com.dutyMS.modules.sys.dictionary.service.SysDictionaryService;
import com.dutyMS.modules.sys.dictionary.vo.SysDictionaryVo;
import com.dutyMS.modules.sys.utils.controller.AbstractController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 字典功能数据层
 * @author Mela.S
 * @date 2020/3/11
 */
@RestController
@RequestMapping("/sys/dictionary")
@Api(tags = "工单类型及部门树-资源管理接口")
public class SysDictionaryController  extends AbstractController {
    @Resource
    private SysDictionaryService sysDictionaryService;
    /**
     * 通过上级查询编码+本级的编码查询出来本级名称
     */
    @GetMapping("/getDicByQueryCode/{queryCode}")
    public R getDicByQueryCode(@NotBlank @PathVariable("queryCode")String queryCode) {
        return R.ok().result(sysDictionaryService.getChildDicByQueryCode(queryCode));
    }
    /**
     * 查询字典列表
     */
    @GetMapping("/list")
    public R list(String code) {
        List<SysDictionaryBo>list = sysDictionaryService.queryList(code);
        return R.ok().result(list);
    }
    @GetMapping("/departmentList/{code}")
    public R departmentList(@NotBlank @PathVariable("code")String code) {
        return R.ok().result(sysDictionaryService.queryDepartmentList(code));
    }

    /**
     * 根据ID主键查询详情信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        SysDictionaryEntity entity = sysDictionaryService.selectById(id);
        return R.ok().put("dictionary", entity);
    }
    /**
     * 字典创建用 选择上级菜单下拉框
     */
    @GetMapping("/select")
    public R select() {
        //查询列表数据
        List<SysDictionaryBo> list = sysDictionaryService.queryList(null);
        //添加顶级菜单
        SysDictionaryBo root = new SysDictionaryBo();
        root.setDictionaryId("-1");
        root.setName("一级字典");
        root.setParentId("-1");
        list.add(root);
        return R.ok().put("dictionaryList", list);
    }

    /**
     * 保存
     */
    @SysLog("保存字典")
    @PostMapping("/save")
    public R save(@RequestBody SysDictionaryVo vo) {
        vo.setCreateUserId(getUserId());
        Integer result = sysDictionaryService.saveEntity(vo);
        if (result==1){
            return R.ok();
        }else if (result==-1){
            return R.error("查询编码不能重复");
        }else{
            return R.error();
        }
    }

    /**
     * 修改
     */
    @SysLog("修改字典")
    @PostMapping("/update")
    public R update(@RequestBody SysDictionaryVo vo) {
        Integer result = sysDictionaryService.updateEntity(vo);
        if (result==1){
            return R.ok();
        }else if (result==-1){
            return R.error("查询编码不能重复");
        }else{
            return R.error();
        }
    }

    /**
     * 删除
     */
    @SysLog("删除字典")
    @PostMapping("/delete/{dictionaryId}")
    public R delete(@PathVariable("dictionaryId") String dictionaryId) {
        sysDictionaryService.deletedInfoById(dictionaryId);
        return R.ok();
    }
    /**
     * 启停操作
     */
    @SysLog("启停字典")
    @PostMapping("/enabled/{dictionaryId}/{onUse}")
    public R delete(@PathVariable("dictionaryId") String dictionaryId, @PathVariable("onUse") Integer onUse) {
        Integer result = sysDictionaryService.enabledById(dictionaryId, onUse);
        if (result==1){
            return R.ok();
        }else{
            return R.error();
        }
    }

}
