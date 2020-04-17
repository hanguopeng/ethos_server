package com.dutyMS.modules.sheet.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sheet.bo.QuerySheetBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.bo.SheetQueryBo;
import com.dutyMS.modules.sheet.entity.SheetEntity;
import com.dutyMS.modules.sys.region.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SheetService extends IService<SheetEntity>{

    Long createSheet(SheetInfoBo bo);

    List<SheetQueryBo> querySheetInfo(QuerySheetBo bo);

    List<Map<String,String>> queryCitys();

    String getSheetNo(String regionId);

    Integer sendSheet(SheetInfoBo bo);
}
