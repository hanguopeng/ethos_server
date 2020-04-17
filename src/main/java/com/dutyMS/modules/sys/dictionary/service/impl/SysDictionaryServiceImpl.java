package com.dutyMS.modules.sys.dictionary.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.utils.DutyUtils;
import com.dutyMS.modules.sys.dictionary.bo.SysDictionaryBo;
import com.dutyMS.modules.sys.dictionary.dao.SysDictionaryDao;
import com.dutyMS.modules.sys.dictionary.entity.SysDictionaryEntity;
import com.dutyMS.modules.sys.dictionary.service.SysDictionaryService;
import com.dutyMS.modules.sys.dictionary.vo.SysDictionaryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典表实现类
 * @author Mela.S
 * @date 2020/3/11
 */
@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryDao, SysDictionaryEntity> implements SysDictionaryService {
    @Resource
    private SysDictionaryDao sysDictionaryDao;
    @Override
    public List<SysDictionaryBo> getChildDicByQueryCode(String queryCode) {
        return sysDictionaryDao.getChildDicByQueryCode(queryCode);
    }

    @Override
    public List<SysDictionaryBo> queryList(String code) {
        List<SysDictionaryBo> list = sysDictionaryDao.getAllDic(code);
        return sysDictionaryDao.getAllDic(code);
    }

    @Override
    public List<SysDictionaryBo> queryDepartmentList(String code) {
        List<SysDictionaryBo> list = sysDictionaryDao.queryDepartmentList(code);
        return sysDictionaryDao.queryDepartmentList(code);
    }

    @Override
    public Integer saveEntity(SysDictionaryVo vo) {
        // 验证queryCode唯一
        if (StringUtils.isNotBlank(vo.getQueryCode())){
            Integer count = sysDictionaryDao.countByQueryCode(vo.getQueryCode());
            if (count>0) return -1;
        }
        SysDictionaryEntity entity = transEntityByVo(vo);
        return sysDictionaryDao.insert(entity);
    }

    @Override
    public Integer updateEntity(SysDictionaryVo vo) {
        // 验证queryCode唯一
        if (StringUtils.isNotBlank(vo.getQueryCode())){
            SysDictionaryEntity entity = sysDictionaryDao.selectById(vo.getDictionaryId());
            if (!vo.getQueryCode().equals(entity.getQueryCode())){
                Integer count = sysDictionaryDao.countByQueryCode(vo.getQueryCode());
                if (count>0) return -1;
            }
        }
        SysDictionaryEntity entity = transEntityByVo(vo);
        return sysDictionaryDao.updateById(entity);
    }

    @Override
    public void deletedInfoById(String dictionaryId) {
        sysDictionaryDao.deleteInfoById(dictionaryId);
    }

    @Override
    public Integer enabledById(String dictionaryId, Integer onUse) {
        return sysDictionaryDao.enabledById(dictionaryId, onUse);
    }

    private SysDictionaryEntity transEntityByVo(SysDictionaryVo vo){
        SysDictionaryEntity entity = new SysDictionaryEntity();
        if (StringUtils.isNotBlank(vo.getDictionaryId()))entity.setDictionaryId(DutyUtils.parseLong(vo.getDictionaryId()));
        if (StringUtils.isNotBlank(vo.getParentId()))entity.setParentId(DutyUtils.parseLong(vo.getParentId()));
        entity.setCreateUserId(vo.getCreateUserId());
        entity.setName(vo.getName());
        entity.setOrderNum(vo.getOrderNum());
        entity.setQueryCode(vo.getQueryCode());
        return entity;
    }
}
