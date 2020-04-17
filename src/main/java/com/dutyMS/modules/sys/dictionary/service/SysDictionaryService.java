package com.dutyMS.modules.sys.dictionary.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sys.dictionary.bo.SysDictionaryBo;
import com.dutyMS.modules.sys.dictionary.entity.SysDictionaryEntity;
import com.dutyMS.modules.sys.dictionary.vo.SysDictionaryVo;

import java.util.List;

/**
 * @author Mela.S
 * @date 2020/3/11
 */
public interface SysDictionaryService extends IService<SysDictionaryEntity> {
    /**
     * 根据code查询下级字典数据
     */
    List<SysDictionaryBo> getChildDicByQueryCode(String queryCode);
    /**
     * 查询字典列表
     */
    List<SysDictionaryBo> queryList(String code);

    /*
    * 查询部门树
    */
    List<SysDictionaryBo> queryDepartmentList(String code);
    /**
     * 新增
     */
    Integer saveEntity(SysDictionaryVo vo);
    /**
     * 修改
     */
    Integer updateEntity(SysDictionaryVo vo);

    /**
     * 软删除指定字典以及下级字典
     */
    void deletedInfoById(String dictionaryId);
    /**
     * 启停
     */
    Integer enabledById(String dictionaryId, Integer onUse);
}
