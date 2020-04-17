package com.dutyMS.modules.sys.region.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 地市实体Bo
 * @author Mela.S
 * @date 2019/12/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysRegionBo")
public class SysRegionBo {
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

    /**
     * 下级地市集合
     */
    private List<SysRegionBo> childRegionList;
}
