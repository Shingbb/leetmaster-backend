package com.shing.leetmaster.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shing.leetmaster.model.dto.questionBank.QuestionBankQueryRequest;
import com.shing.leetmaster.model.entity.QuestionBank;
import com.shing.leetmaster.model.vo.QuestionBankVO;

/**
 * 题库服务
 *
 * @author shing
 */
public interface QuestionBankService extends IService<QuestionBank> {

    /**
     * 校验数据
     *
     * @param questionBank 数据
     * @param add 对创建的数据进行校验
     */
    void validQuestionBank(QuestionBank questionBank, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionBankQueryRequest 查询条件
     * @return QueryWrapper
     */
    QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);
    
    /**
     * 获取题库封装
     *
     * @param questionBank 题库实体
     * @return QuestionBankVO
     */
    QuestionBankVO getQuestionBankVO(QuestionBank questionBank);

    /**
     * 分页获取题库封装
     *
     * @param questionBankPage 分页数据
     * @return Page<QuestionBankVO>
     */
    Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage);
}
