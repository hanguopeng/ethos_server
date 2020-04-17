package com.dutyMS.modules.sys.works.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.works.bo.SysWorksBo;
import com.dutyMS.modules.sys.works.entity.SysWorksEntity;
import com.dutyMS.modules.sys.works.vo.SysWorksVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 任务项dao层
 *
 * @author Mela.S
 * @date 2020/3/17
 */
@Mapper
@Component
public interface SysWorksDao extends BaseMapper<SysWorksEntity> {

    /**
     * 获取符合条件的所有任务项
     */
    List<SysWorksBo> getWorksListByVo(SysWorksVo vo);

    /**
     * 获取符合条件的所有任务项数量
     */
    Integer countWorksListByVo(SysWorksVo vo);

    /**
     * 软删除指定任务项
     */
    Integer deleteInfoById(String workId);
    /**
     * 通过id获取指定任务项信息
     */
    SysWorksBo getWorkInfoById(String workId);
}
