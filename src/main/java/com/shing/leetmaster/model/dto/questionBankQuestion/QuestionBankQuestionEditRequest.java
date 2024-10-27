package com.shing.leetmaster.model.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 编辑题库题目请求
 *
 * @author shing
 */
@Data
public class QuestionBankQuestionEditRequest implements Serializable {

    private Long id;

    private Long questionBankId;

    private Long questionId;

    private Long userId;

    private Integer questionOrder;
    
    private static final long serialVersionUID = 1L;
}