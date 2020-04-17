package com.dutyMS.modules.sheet.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessStatusBo {

    //状态名
    private String statusName;

    //状态码
    private Long statusCode;
}
