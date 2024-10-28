package com.shing.leetmaster.model.dto.question;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shing.leetmaster.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 查询题目请求
 *
 * @author shing
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    private String answer;

    /**
     * 创建用户 id
     */
    private Long userId;


    /**
     * 审核状态（0-待审核, 1-审核通过, 2-审核不通过）
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

    /**
     * 搜索词
     */
    private String searchText;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}