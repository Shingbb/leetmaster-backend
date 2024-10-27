package com.shing.leetmaster.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shing.leetmaster.model.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import com.shing.leetmaster.model.entity.QuestionBankQuestion;
import com.shing.leetmaster.model.vo.QuestionBankQuestionVO;

/**
 * 题库题目服务
 *
 * @author shing
 */
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {

    /**
     * 校验数据
     *
     * @param questionBankQuestion 数据
     * @param add                  对创建的数据进行校验
     */
    void validQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionBankQuestionQueryRequest 查询条件
     * @return QueryWrapper
     */
    QueryWrapper<QuestionBankQuestion> getQueryWrapper(QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest);

    /**
     * 获取题库题目封装
     *
     * @param questionBankQuestion 题库题目实体
     * @return QuestionBankQuestionVO
     */
    QuestionBankQuestionVO getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion);

    /**
     * 分页获取题库题目封装
     *
     * @param questionBankQuestionPage 分页数据
     * @return Page<QuestionBankQuestionVO>
     */
    Page<QuestionBankQuestionVO> getQuestionBankQuestionVOPage(Page<QuestionBankQuestion> questionBankQuestionPage);
}
