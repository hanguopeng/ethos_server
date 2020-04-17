package com.dutyMS.modules.sys.works.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


/**
 * 任务项Vo
 * @author Mela.S
 * @date 2020/3/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysWorksVo")
public class SysWorksVo {
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
     * 登录人
     */
    private Long loginUser;

    private Integer page = 1;
    private Integer limit = 10;
    private Integer start = 0;

    public Integer getStart() {
        if ((this.page - 1) * this.limit != 0) {
            return (this.page - 1) * this.limit;
        } else {
            return start;
        }
    }
}
