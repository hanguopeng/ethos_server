package com.dutyMS.modules.sheet.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuerySheetBo {

    //开始日期
    private String startDate;
    //结束日期
    private String endDate;
    //所属地市---地市代码
    private String belongToCity;
    //工单状态
    private String sheetStatus;
    //工单编号
    private String sheetNo;
    //用户id---查询用户待办工单
    private Long userId;

}
