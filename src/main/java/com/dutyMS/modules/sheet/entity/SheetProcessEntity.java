package com.dutyMS.modules.sheet.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("SHEET_PROCESS")
public class SheetProcessEntity {

    //流程id
    @TableId(type= IdType.AUTO)
    private Long processId;

    //工单id
    private Long sheetId;
    //流程初始id
    private Long processStartId;
    //流程结束id
    private Long processEndId;
    //流程创建时间
    private Date createTime;
    //流程结束时间
    private Date endTime;
    //当前状态
    private Long currentStatus;
    //当前节点处理人id
    private Long nodePersonId;
    //当前节点处理人姓名
    private String nodePersonName;
    //当前节点处理人所属角色
    private String nodePersonRole;
    //处理意见
    private String resolveSuggestion;
    //当前处理人联系方式
    private String nodePersonContact;
    //流程操作时间
    private Date operationTime;
}

