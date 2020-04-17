package com.dutyMS.modules.sys.region.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.region.bo.SysRegionBo;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 地市Dao层
 * @author Mela.S
 * @date 2019/12/20
 */
@Mapper
@Component
public interface SysRegionDao extends BaseMapper<SysRegionEntity> {
    /**
     * 通过用户Id获取所属地市（如果属于省，则需要关联下级市）
     * @param userId
     * @return
     */
    SysRegionBo getRegionInfoByUserId(Long userId);

    /**
     * 根据地市父id获取子数据
     * @param parentRegionId
     * @return
     */
    List<SysRegionBo> getChildRegionsByParentRegionId(Long parentRegionId);

}
