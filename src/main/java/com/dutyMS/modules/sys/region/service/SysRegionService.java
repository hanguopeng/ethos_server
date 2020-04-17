package com.dutyMS.modules.sys.region.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sys.region.bo.SysRegionBo;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;

/**
 * 地市管理Service
 * @author Mela.S
 * @date 2019/12/20
 */
public interface SysRegionService  extends IService<SysRegionEntity> {
    /**
     * 通过用户Id获取所属地市（如果属于省，则需要关联下级市）
     * @param userId
     * @return
     */
    SysRegionBo getRegionInfoByUserId(Long userId);
}
