package com.shing.leetmaster.model.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新题库题目请求
 *
 * @author shing
 */
@Data
public class QuestionBankQuestionUpdateRequest implements Serializable {

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
    
    private static final long serialVersionUID = 1L;
}