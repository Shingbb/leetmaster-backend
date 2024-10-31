package com.shing.leetmaster.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.shing.leetmaster.common.*;
import com.shing.leetmaster.constant.UserConstant;
import com.shing.leetmaster.exception.BusinessException;
import com.shing.leetmaster.exception.ThrowUtils;
import com.shing.leetmaster.model.dto.question.QuestionQueryRequest;
import com.shing.leetmaster.model.dto.questionBank.*;
import com.shing.leetmaster.model.entity.Question;
import com.shing.leetmaster.model.entity.QuestionBank;
import com.shing.leetmaster.model.entity.User;
import com.shing.leetmaster.model.enums.ReviewStatusEnum;
import com.shing.leetmaster.model.vo.QuestionBankVO;
import com.shing.leetmaster.service.QuestionBankService;
import com.shing.leetmaster.service.QuestionService;
import com.shing.leetmaster.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 题库接口
 *
 * @author shing
 */
@RestController
@RequestMapping("/questionBank")
@Slf4j
@Api(tags = "题库接口")
public class QuestionBankController {

    @Resource
    private QuestionBankService questionBankService;

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建题库
     *
     * @param questionBankAddRequest 创建题库请求
     * @return {@link BaseResponse }<{@link Long }>
     */
    @PostMapping("/add")
    @ApiOperation(value = "创建题库")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addQuestionBank(@RequestBody QuestionBankAddRequest questionBankAddRequest) {
        ThrowUtils.throwIf(questionBankAddRequest == null, ErrorCode.PARAMS_ERROR);
        // todo 在此处将实体类和 DTO 进行转换
        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankAddRequest, questionBank);
        // 数据校验
        questionBankService.validQuestionBank(questionBank, true);
        // todo 填充默认值
        User loginUser = userService.getLoginUser();
        questionBank.setUserId(loginUser.getId());
        // 写入数据库
        boolean result = questionBankService.save(questionBank);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        // 返回新写入的数据 id
        long newQuestionBankId = questionBank.getId();
        return ResultUtils.success(newQuestionBankId);
    }

    /**
     * 删除题库
     *
     * @param deleteRequest 删除题库请求
     * @return {@link BaseResponse }<{@link Boolean }>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除题库")
    public BaseResponse<Boolean> deleteQuestionBank(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser();
        long id = deleteRequest.getId();
        // 判断是否存在
        QuestionBank oldQuestionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(oldQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldQuestionBank.getId().equals(user.getId()) && !userService.isAdmin()) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = questionBankService.removeById(id);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新题库（仅管理员可用）
     *
     * @param questionBankUpdateRequest 更新题库请求
     * @return {@link BaseResponse }<{@link Boolean }>
     */
    @PostMapping("/update")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "更新题库（仅管理员可用）")
    public BaseResponse<Boolean> updateQuestionBank(@RequestBody QuestionBankUpdateRequest questionBankUpdateRequest) {
        if (questionBankUpdateRequest == null || questionBankUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankUpdateRequest, questionBank);
        // 数据校验
        questionBankService.validQuestionBank(questionBank, false);
        // 判断是否存在
        long id = questionBankUpdateRequest.getId();
        QuestionBank oldQuestionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(oldQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = questionBankService.updateById(questionBank);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 根据 id 获取题库（封装类）
     *
     * @param id 题库 id
     * @return {@link BaseResponse }<{@link QuestionBankVO }>
     */
    @GetMapping("/get/vo/id")
    @ApiOperation(value = "仅根据 id 获取题库（封装类）")
    public BaseResponse<QuestionBankVO> getQuestionBankVOByIdOnly(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        QuestionBank questionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(questionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 获取封装类
        return ResultUtils.success(questionBankService.getQuestionBankVO(questionBank));
    }

    /**
     * 根据 id 获取题库（封装类）
     *
     * @param questionBankIdQueryRequest 题库 id
     * @return {@link BaseResponse }<{@link QuestionBankVO }>
     */
    @GetMapping("/get/vo")
    @ApiOperation(value = "根据 id 获取题库（封装类）")
    public BaseResponse<QuestionBankVO> getQuestionBankVOById(QuestionBankIdQueryRequest questionBankIdQueryRequest) {
        ThrowUtils.throwIf(questionBankIdQueryRequest == null, ErrorCode.NOT_FOUND_ERROR);
        Long id = questionBankIdQueryRequest.getQuestionBankId();
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        QuestionBank questionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(questionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 查询题库封装类
        QuestionBankVO questionBankVO = questionBankService.getQuestionBankVO(questionBank);
        // 是否要关联查询题库下的题目列表
        boolean needQueryQuestionList = questionBankIdQueryRequest.isNeedQueryQuestionList();
        // 如果需要查询问题列表
        if (needQueryQuestionList) {
            // 创建一个 question 查询请求对象
            QuestionQueryRequest questionQueryRequest = new QuestionQueryRequest();
            // 设置题库ID
            questionQueryRequest.setQuestionBankId(id);
            // 调用服务方法，按页列表查询问题
            Page<Question> questionPage = questionService.listQuestionByPage(questionQueryRequest);
            // 设置 question 页面到 question 视图对象
            questionBankVO.setQuestionPage(questionPage);
        }
        // 获取封装类
        return ResultUtils.success(questionBankVO);
    }

    /**
     * 分页获取题库列表（仅管理员可用）
     *
     * @param questionBankQueryRequest 分页获取题库列表请求
     * @return {@link BaseResponse }<{@link Page }<{@link QuestionBank }>>
     */
    @PostMapping("/list/page")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @ApiOperation(value = "分页获取题库列表（仅管理员可用）")
    public BaseResponse<Page<QuestionBank>> listQuestionBankByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        long current = questionBankQueryRequest.getCurrent();
        long size = questionBankQueryRequest.getPageSize();
        // 查询数据库
        Page<QuestionBank> questionBankPage = questionBankService.page(new Page<>(current, size),
                questionBankService.getQueryWrapper(questionBankQueryRequest));
        return ResultUtils.success(questionBankPage);
    }

    /**
     * 分页获取题库列表（封装类）
     *
     * @param questionBankQueryRequest 分页获取题库列表请求
     * @return {@link BaseResponse }<{@link Page }<{@link QuestionBankVO }>>
     */
    @PostMapping("/list/page/vo")
    @ApiOperation(value = "分页获取题库列表（封装类）")
    public BaseResponse<Page<QuestionBankVO>> listQuestionBankVOByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        long current = questionBankQueryRequest.getCurrent();
        long size = questionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Page<QuestionBank> questionBankPage = questionBankService.page(new Page<>(current, size),
                questionBankService.getQueryWrapper(questionBankQueryRequest));
        // 获取封装类
        return ResultUtils.success(questionBankService.getQuestionBankVOPage(questionBankPage));
    }

    /**
     * 分页获取当前登录用户创建的题库列表
     *
     * @param questionBankQueryRequest 分页获取题库列表请求
     * @return {@link BaseResponse }<{@link Page }<{@link QuestionBankVO }>>
     */
    @PostMapping("/my/list/page/vo")
    @ApiOperation(value = "分页获取当前登录用户创建的题库列表")
    public BaseResponse<Page<QuestionBankVO>> listMyQuestionBankVOByPage(@RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        ThrowUtils.throwIf(questionBankQueryRequest == null, ErrorCode.PARAMS_ERROR);
        // 补充查询条件，只查询当前登录用户的数据
        User loginUser = userService.getLoginUser();
        questionBankQueryRequest.setId(loginUser.getId());
        long current = questionBankQueryRequest.getCurrent();
        long size = questionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Page<QuestionBank> questionBankPage = questionBankService.page(new Page<>(current, size),
                questionBankService.getQueryWrapper(questionBankQueryRequest));
        // 获取封装类
        return ResultUtils.success(questionBankService.getQuestionBankVOPage(questionBankPage));
    }

    /**
     * 编辑题库（给用户使用）
     *
     * @param questionBankEditRequest 编辑题库请求
     * @return {@link BaseResponse }<{@link Boolean }>
     */
    @PostMapping("/edit")
    @ApiOperation(value = "编辑题库（给用户使用）")
    public BaseResponse<Boolean> editQuestionBank(@RequestBody QuestionBankEditRequest questionBankEditRequest) {
        if (questionBankEditRequest == null || questionBankEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 在此处将实体类和 DTO 进行转换
        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankEditRequest, questionBank);
        // 数据校验
        questionBankService.validQuestionBank(questionBank, false);
        User loginUser = userService.getLoginUser();
        // 判断是否存在
        long id = questionBankEditRequest.getId();
        QuestionBank oldQuestionBank = questionBankService.getById(id);
        ThrowUtils.throwIf(oldQuestionBank == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldQuestionBank.getId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 操作数据库
        boolean result = questionBankService.updateById(questionBank);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    // endregion

    /**
     * 题目审核
     */
    @PostMapping("/review")
    @ApiOperation(value = "审核题库")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> reviewQuestion(@RequestBody ReviewRequest reviewRequest) {
        // 检查请求参数是否为 null
        ThrowUtils.throwIf(reviewRequest == null, ErrorCode.PARAMS_ERROR);
        // 获取请求中的 ID 和审核状态
        Long id = reviewRequest.getId();
        Integer reviewStatus = reviewRequest.getReviewStatus();
        // 参数校验
        ReviewStatusEnum reviewStatusEnum = ReviewStatusEnum.getEnumByValue(reviewStatus);
        if (id == null || reviewStatusEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //判断要审核的信息是否存在
        Question oldQuestion = questionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);
        //如果审核状态传入的状态一样，则不进行审核
        if (oldQuestion.getReviewStatus().equals(reviewStatus)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请勿重复审核");
        }
        //更新审核状态
        Question question = new Question();
        question.setId(id);
        question.setReviewStatus(reviewStatus);
        question.setReviewMessage(reviewRequest.getReviewMessage());

        // 操作数据库
        boolean result = questionService.updateById(question);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
