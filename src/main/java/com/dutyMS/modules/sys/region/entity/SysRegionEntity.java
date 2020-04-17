package com.dutyMS.modules.sys.region.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 地市实体
 * @author Mela.S
 * @date 2019/12/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysRegionEntity")
@TableName("sys_region_city")
public class SysRegionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 地市Id
     */
    private Long regionId;
    /**
     * 地市名称
     */
    private String regionName;
    /**
     * 上级地市Id
     */
    private Long parentRegionId;
}
