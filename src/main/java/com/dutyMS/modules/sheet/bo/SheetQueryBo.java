package com.dutyMS.modules.sheet.bo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SheetQueryBo {

    private Long id;    //工单id

    private String sheetNo;     //工单编号

    private String sheetTheme;      //工单主题

    private Date happenTime;        //发生时间

    private String relationDepartment;      //涉及机构

    private String reflectionProblem;     //反映问题

    private String sendPerson;      //派往对象

    private String sheetLink;       //链接

    private String city;        //城市

    private String sheetType;       //工单类型

    private String relationPerson;      //涉及人员

    private Date reflectionTime;        //反应时间

    private String relationEvent;       //关联事件

    private String enclosureUrl;        //附件地址

    private String resolveTime;      //处理时限

    private String isOverTime;      //是否超时

    private String currentStatus;       //当前状态

    private Long createPersonId;      //创建人ID

    private String createPersonName;        //创建人姓名

    private Date createTime;        //创建时间

    private Long filePersonId;      //归档人ID

    private String filePersonName;      //归档人姓名

    private Date fileTime;      //归档时间

    private String fileSuggestion;      //归档意见


    private String createPersonCity;    //建单人所属地市

    private Long nodePersonId;    //当前节点处理人id

    private String nodePersonName;      //当前节点处理人姓名

    private String statusCode;      //工单当前状态码
}
