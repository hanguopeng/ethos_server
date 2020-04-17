package com.dutyMS.modules.sheet.service;

import com.baomidou.mybatisplus.service.IService;
import com.dutyMS.modules.sheet.bo.ProcessInfoBo;
import com.dutyMS.modules.sheet.bo.SheetInfoBo;
import com.dutyMS.modules.sheet.entity.SheetProcessEntity;
import com.dutyMS.modules.sheet.vo.SheetProcessVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SheetProcessService extends IService<SheetProcessEntity>{

    Integer sendSheet(SheetInfoBo bo);

    Integer orderTask(Long sheetId);

    List<ProcessInfoBo> queryProcess(String sheetId);

    Integer resolveTask(SheetProcessVo vo);
}
