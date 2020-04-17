package com.dutyMS.modules.sheet.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dutyMS.modules.sheet.bo.ProcessInfoBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.entity.SheetProcessEntity;
import com.dutyMS.modules.sheet.vo.SheetProcessVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SheetProcessDao extends BaseMapper<SheetProcessEntity>{

    Integer sendSheet(SheetProcessEntity bo);

    Integer updateProcessStartId(SheetProcessEntity entity);

    SheetProcessEntity selectBySheetId(Long sheetId);

    Integer orderTask(Long sheetId);

    SheetProcessEntity queryProcessBySheetNo(Long sheetId);

    List<ProcessInfoBo> queryProcess(String sheetId);

    Integer resolveTask(SheetProcessVo vo);
}
