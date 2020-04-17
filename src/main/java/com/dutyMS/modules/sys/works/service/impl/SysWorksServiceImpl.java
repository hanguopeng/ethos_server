package com.dutyMS.modules.sys.works.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.common.utils.DutyUtils;
import com.dutyMS.modules.sys.works.bo.SysWorksBo;
import com.dutyMS.modules.sys.works.dao.SysWorksDao;
import com.dutyMS.modules.sys.works.entity.SysWorksEntity;
import com.dutyMS.modules.sys.works.service.SysWorksService;
import com.dutyMS.modules.sys.works.vo.SysWorksVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 任务项实现类
 * @author Mela.S
 * @date 2020/3/17
 */
@Service("sysWorksService")
public class SysWorksServiceImpl extends ServiceImpl<SysWorksDao, SysWorksEntity> implements SysWorksService {
    @Resource
    private SysWorksDao sysWorksDao;

    @Override
    public List<SysWorksBo> getWorksListByVo(SysWorksVo vo) {
        return sysWorksDao.getWorksListByVo(vo);

    }

    @Override
    public Integer countWorksListByVo(SysWorksVo vo) {
        return sysWorksDao.countWorksListByVo(vo);
    }

    @Override
    public Integer saveEntity(SysWorksVo vo) {
        SysWorksEntity entity = new SysWorksEntity();
        entity.setWorkName(vo.getWorkName());
        entity.setUrl(vo.getUrl());
        entity.setCreateUserId(vo.getLoginUser());
        entity.setUpdateUserId(vo.getLoginUser());
        return sysWorksDao.insert(entity);
    }

    @Override
    public Integer updateEntity(SysWorksVo vo) {
        SysWorksEntity entity = new SysWorksEntity();
        entity.setWorkId(DutyUtils.parseLong(vo.getWorkId()));
        entity.setWorkName(vo.getWorkName());
        entity.setUrl(vo.getUrl());
        entity.setUpdateUserId(vo.getLoginUser());
        entity.setUpdateTime(new Date());
        return sysWorksDao.updateById(entity);
    }

    @Override
    public Integer deleteInfoById(String workId) {
        return sysWorksDao.deleteInfoById(workId);
    }

    @Override
    public SysWorksBo getWorkInfoById(String workId) {
        return sysWorksDao.getWorkInfoById(workId);
    }
}
