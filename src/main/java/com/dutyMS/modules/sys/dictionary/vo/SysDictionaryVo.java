package com.dutyMS.modules.sys.dictionary.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


/**
 * 字典Vo
 * @author Mela.S
 * @date 2020/3/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysDictionaryVo")
public class SysDictionaryVo {
    /**
     * 字典表id
     */
    private String dictionaryId;
    /**
     * 父字典Id
     */
    private String parentId;
    /**
     * 查询CODE
     */
    private String queryCode;
    /**
     * 显示名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 创建人姓名
     */
    private Long createUserId;

}
