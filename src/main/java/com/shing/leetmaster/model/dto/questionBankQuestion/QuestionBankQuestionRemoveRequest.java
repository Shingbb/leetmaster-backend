package com.shing.leetmaster.model.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除题目题库关系请求
 * @author shing
 */
@Data
public class QuestionBankQuestionRemoveRequest implements Serializable {
    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}