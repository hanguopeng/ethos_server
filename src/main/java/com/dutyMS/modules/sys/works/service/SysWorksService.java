package com.dutyMS.modules.sys.works.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sys.works.bo.SysWorksBo;
import com.dutyMS.modules.sys.works.entity.SysWorksEntity;
import com.dutyMS.modules.sys.works.vo.SysWorksVo;

import java.util.List;

/**
 * @author Mela.S
 * @date 2020/3/17
 */
public interface SysWorksService extends IService<SysWorksEntity> {

    /**
     * 获取符合条件的所有任务项
     */
    List<SysWorksBo> getWorksListByVo(SysWorksVo vo);

    /**
     * 获取符合条件的所有任务项数量
     */
    Integer countWorksListByVo(SysWorksVo vo);
    /**
     * 新增
     */
    Integer saveEntity(SysWorksVo vo);
    /**
     * 修改
     */
    Integer updateEntity(SysWorksVo vo);

    /**
     * 软删除指定任务项
     */
    Integer deleteInfoById(String workId);

    /**
     * 通过id获取指定任务项信息
     */
    SysWorksBo getWorkInfoById(String workId);


}
