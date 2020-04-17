package com.dutyMS.modules.sys.region.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dutyMS.modules.sys.region.bo.SysRegionBo;
import com.dutyMS.modules.sys.region.dao.SysRegionDao;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;
import com.dutyMS.modules.sys.region.service.SysRegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 地市管理service实现类
 * @author Mela.S
 * @date 2019/12/20
 */
@Service("sysRegionService")
public class SysRegionServiceImpl extends ServiceImpl<SysRegionDao, SysRegionEntity> implements SysRegionService {
    @Resource
    private SysRegionDao sysRegionDao;
    @Override
    public SysRegionBo getRegionInfoByUserId(Long userId) {
        SysRegionBo sysRegionBo = sysRegionDao.getRegionInfoByUserId(userId);
        if (sysRegionBo!=null){
            if(sysRegionBo.getParentRegionId()==-1){
                sysRegionBo.setChildRegionList(sysRegionDao.getChildRegionsByParentRegionId(sysRegionBo.getRegionId()));
            }
            return sysRegionBo;
        }else{
            return null;
        }
    }
}
