package com.shing.leetmaster.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shing.leetmaster.model.dto.question.QuestionQueryRequest;
import com.shing.leetmaster.model.entity.Question;
import com.shing.leetmaster.model.vo.QuestionVO;

/**
 * 题目服务
 *
 * @author shing
 */
public interface QuestionService extends IService<Question> {

    /**
     * 校验数据
     *
     * @param question 数据
     * @param add 对创建的数据进行校验
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest 查询条件
     * @return QueryWrapper
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);
    
    /**
     * 获取题目封装
     *
     * @param question 题目实体
     * @return QuestionVO
     */
    QuestionVO getQuestionVO(Question question);

    /**
     * 分页获取题目封装
     *
     * @param questionPage 分页数据
     * @return Page<QuestionVO>
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage);
}
