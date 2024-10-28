package com.shing.leetmaster.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 题库题目
 * @TableName question_bank_question
 */
@TableName("question_bank_question")
@Data
public class QuestionBankQuestion implements Serializable {

    /**
     * id（要指定主键策略）雪花算法
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 题目顺序（题号）
     */
    private Integer questionOrder;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}