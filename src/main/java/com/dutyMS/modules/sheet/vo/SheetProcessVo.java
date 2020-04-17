package com.dutyMS.modules.sheet.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SheetProcessVo {

    private String remarks;     //工单处问题描述

    private Long sheetId;       //工单id

}
