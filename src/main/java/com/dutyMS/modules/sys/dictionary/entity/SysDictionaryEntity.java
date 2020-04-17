package com.dutyMS.modules.sys.dictionary.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 字典表实体
 * @author Mela.S
 * @date 2020/3/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysDictionaryEntity")
@TableName("sys_dictionary")
public class SysDictionaryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 字典表id
     */
    @TableId(type = IdType.AUTO)
    private Long dictionaryId;
    /**
     * 父字典Id
     */
    private Long parentId;
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
     * 创建人id
     */
    private Long createUserId;

}
