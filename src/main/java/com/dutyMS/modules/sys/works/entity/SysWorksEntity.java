package com.dutyMS.modules.sys.works.entity;

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

/**
 * 任务项实体
 * @author Mela.S
 * @date 2020/3/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysWorksEntity")
@TableName("zb_sys_works")
public class SysWorksEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 任务项表id
     */
    @TableId(type = IdType.ID_WORKER)
    private Long workId;
    /**
     * 任务项名称
     */
    private String workName;
    /**
     * 任务项跳转的地址
     */
    private String url;
    /**
     * 创建人id
     */
    private Long createUserId;
    /**
     * 创建人id
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private Date updateTime;

}
