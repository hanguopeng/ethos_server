package com.dutyMS.modules.sys.works.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 任务项字典Bo
 * @author Mela.S
 * @date 2020/3/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysWorksBo")
public class SysWorksBo {
    /**
     * 任务项表id
     */
    private String workId;
    /**
     * 任务项名称
     */
    private String workName;
    /**
     * 任务项跳转的地址
     */
    private String url;
    /**
     * 最后修改人姓名
     */
    private String updateUserName;
    /**
     * 最后修改时间
     */
    private Date updateTime;
}
