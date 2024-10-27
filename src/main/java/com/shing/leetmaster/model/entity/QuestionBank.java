package com.shing.leetmaster.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 题库
 * @TableName question_bank
 */
@TableName("question_bank")
@Data
public class QuestionBank implements Serializable {

    /**
     * id（要指定主键策略）雪花算法
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String picture;

    /**
     * 创建用户 id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long userId;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态：0-待审核, 1-通过, 2-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

    /**
     * 审核人 id
     */
    private Long reviewerId;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 浏览量
     */
    private Integer viewNum;

    /**
     * 是否删除（逻辑删除） 0-未删除，1-已删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}