package com.dutyMS.modules.sys.log.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sys.log.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-08 10:40:56
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

}
