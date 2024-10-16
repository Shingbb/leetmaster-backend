package com.shing.leetmaster.model.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 题库题目
 * @TableName question_bank_question
 */
@Data
public class QuestionBankQuestion implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
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

    private static final long serialVersionUID = 1L;
}