package com.dutyMS.modules.file.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("fileVo")
public class FileVo {

    private Long id;

    private Long sheetInfoId;      //关联工单id

    private String sheetInfoNo;     //关联工单编号

    private Date uploadTime;      //上传时间

    private String storagePath;     //存储路径

    private String uploadPerson;    //上传人
}
