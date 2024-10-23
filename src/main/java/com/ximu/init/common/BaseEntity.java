package com.ximu.init.common;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 析木
 * @description: 实体类基础属性
 */
@Data
public class BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT) // 结合配置的内容实现自动填充时间
    @JsonIgnore // 在查询的结果中忽略此字段的序列化。原理：@restController注解的作用，是将所有请求方法的返回值，
    private Date createTime; // 序列化成字符串再返回前端，此时使用的序列化框架与@JsonIgnore同为jackson

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonIgnore
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField("is_deleted")
    @JsonIgnore
    @TableLogic //配置mybatis-plus逻辑删除的字段，可实现删除变为更新逻辑删除状态，默认为0未删除，1删除。
    private Byte isDeleted; // 注意：逻辑删除功能只对mybatis-plus自动注入的SQL有效

}