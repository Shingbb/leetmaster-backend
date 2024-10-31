package com.shing.leetmaster.model.dto.questionBank;

import com.shing.leetmaster.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 根据 id 查询题库请求
 *
 * @author shing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionBankIdQueryRequest extends PageRequest implements Serializable {

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 是否要关联查询题目列表
     */
    private  boolean needQueryQuestionList;

    private static final long serialVersionUID = 1L;
}