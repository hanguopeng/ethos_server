package com.dutyMS.modules.sys.dictionary.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 字典Bo
 * @author Mela.S
 * @date 2020/3/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysDictionaryBo")
public class SysDictionaryBo {
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
     * 启停标识
     */
    private Integer onUse;
    /**
     * 创建人姓名
     */
    private String createUserName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 下级字典数据集合
     */
    private List<SysDictionaryBo> sysDictionaryBos;

    private String label;

    private String value;
}
