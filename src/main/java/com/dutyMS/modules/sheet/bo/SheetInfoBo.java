package com.dutyMS.modules.sheet.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SheetInfoBo {
    //新建工单后返回的工单id
    private Long sheetId;

    private String sheetNo;     //工单编号

    private String sheetTheme;      //工单主题

    private String happenTime;        //发生时间

    private String relationDepartment;      //涉及机构

    private String relationProblem;     //反映问题

    private Long sendPerson;      //派往对象

    private String sheetLink;       //链接

    private String city;        //城市

    private String sheetType;       //工单类型

    private String relationPerson;      //涉及人员

    private String reflectionTime;        //反应时间

    private String relationEvent;       //关联事件

    private String enclosureUrl;        //附件地址

    private Long userId;        //创建人ID

    private String userName;        //创建人姓名

    private String creteUserRole;   //创建人所属角色

    private Boolean isDraft;        //是否为草稿

    private String createPersonCity;    //建单人所属地市

    private String currentStatus;   //当前状态

    private String suggestion;      //工单处理意见或备注
}
