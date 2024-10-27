package com.shing.leetmaster.model.dto.questionBankQuestion;

import com.shing.leetmaster.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询题库题目请求
 *
 * @author shing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionBankQuestionQueryRequest extends PageRequest implements Serializable {

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