package com.dutyMS.modules.file.controller.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("fileStorageEntity")
@TableName("resource_file")
public class FileStorageEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long sheetInfoId;      //关联工单id

    private String sheetInfoNo;     //关联工单编号

    private Date uploadTime;      //上传时间

    private String storagePath;     //存储路径

    private String uploadPerson;    //上传人

    private String fileType;        //附件类型
}
