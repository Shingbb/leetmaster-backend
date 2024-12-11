package com.shing.leetmaster.model.dto.question;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目批量删除请求
 *
 * @author Shing
 */
@Data
public class QuestionBatchDeleteRequest implements Serializable {

    /**
     * 题目 id 列表
     */
    private List<Long> questionIdList;

    private static final long serialVersionUID = 1L;
}
