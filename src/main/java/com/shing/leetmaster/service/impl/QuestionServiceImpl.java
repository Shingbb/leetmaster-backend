package com.shing.leetmaster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shing.leetmaster.model.entity.Question;
import com.shing.leetmaster.service.QuestionService;
import com.shing.leetmaster.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author LBC
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-10-16 15:43:56
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




