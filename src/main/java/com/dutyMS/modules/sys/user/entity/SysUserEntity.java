package com.dutyMS.modules.sys.user.entity;

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
 * 系统用户
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("sysUserEntity")
@TableName("sys_user")
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Long userId;
    /**
     * 原用户表ID
     */
    private String pUserId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 密码
     */
    private String password;
    /**
     * email
     */
    private String email;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 盐
     */
    private String salt;
    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;
    /**
     * 创建者ID
     */
    private Long createUserId;
    /**
     * 所属地市
     */
    private String regionId;

    /*
    * 部门id
    * */
    private String departmentId;

    /*
    * 部门名称
    * */
    private String departmentName;

    /*
    * 上级部门id
    * */
    private String parementId;
}
