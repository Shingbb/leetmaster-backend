package com.shing.leetmaster.model.dto.questionBankQuestion;


import lombok.Data;

import java.util.List;

/**
 * 批量创建题库题目请求
 */
@Data
public class QuestionBankQuestionBatchAddRequest {

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id 列表
     */
    private List<Long> questionIdList;

    private static final long serialVersionUID = 1L;

}
