package com.dutyMS.modules.sys.dictionary.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.dictionary.bo.SysDictionaryBo;
import com.dutyMS.modules.sys.dictionary.entity.SysDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典dao层
 *
 * @author Mela.S
 * @date 2020/3/11
 */
@Mapper
@Component
public interface SysDictionaryDao  extends BaseMapper<SysDictionaryEntity> {
    /**
     * 根据code查询下级字典数据
     */
    List<SysDictionaryBo> getChildDicByQueryCode(String queryCode);
    /**
     * 查询指定parentId的子级
     */
    List<SysDictionaryBo> getDicByParentId(String parentId);
    /**
     * 获取所有字典值
     */
    List<SysDictionaryBo> getAllDic(@Param("code")String code);
    /*
    * 查询部门树
    * */
    List<SysDictionaryBo> queryDepartmentList(@Param("code")String code);
    /**
     * 统计指定code的数量
     */
    Integer countByQueryCode(String queryCode);

    /**
     * 软删除指定字典以及下级字典
     */
    void deleteInfoById(String dictionaryId);
    /**
     * 启停
     */
    Integer enabledById(@Param("dictionaryId") String dictionaryId,@Param("onUse") Integer onUse);
}
