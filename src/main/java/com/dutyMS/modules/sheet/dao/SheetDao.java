package com.dutyMS.modules.sheet.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sheet.bo.ProcessStatusBo;
import com.dutyMS.modules.sheet.bo.QuerySheetBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.bo.SheetQueryBo;
import com.dutyMS.modules.sheet.entity.SheetEntity;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SheetDao extends BaseMapper<SheetEntity> {

    Integer createSheet(SheetInfoBo bo);

    List<SheetQueryBo> querySheetInfo(QuerySheetBo bo);

    List<SysRegionEntity> queryCitys();

    String getSheetNoById(@Param("regionId") String regionId);

    Long getSheetCountByCity(@Param("regionId") String regionId);

    Integer sendSheet();

    List<ProcessStatusBo> queryStatusCode();

    SheetEntity queryBySheetNo(QuerySheetBo bo);

    SheetEntity querySheetById(@Param("sheetId") Long sheetId);

    Integer updateStatusBySheetId(@Param("statusCode")Long statusCode,@Param("sheetId") Long sheetId);
}
