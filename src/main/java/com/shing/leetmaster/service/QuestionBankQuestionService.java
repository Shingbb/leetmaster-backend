package com.shing.leetmaster.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shing.leetmaster.model.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import com.shing.leetmaster.model.entity.QuestionBankQuestion;
import com.shing.leetmaster.model.entity.User;
import com.shing.leetmaster.model.vo.QuestionBankQuestionVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 批量添加题目到题库
     *
     * @param questionIdList 题目id列表
     * @param questionBankId 题库id
     * @param loginUser 登录用户
     */
    void batchAddQuestionsToBank(List<Long> questionIdList, long questionBankId, User loginUser);

    /**
     * 批量从题库移除题目
     *
     * @param questionIdList 题目id列表
     * @param questionBankId 题库id
     */
    void batchRemoveQuestionsFromBank(List<Long> questionIdList, long questionBankId);

    /**
     * 批量添加题目到题库（事务，仅供内部调用）
     *
     * @param questionBankQuestions 题库题目列表
     */
    @Transactional(rollbackFor = Exception.class)
    void batchAddQuestionsToBankInner(List<QuestionBankQuestion> questionBankQuestions);
}
