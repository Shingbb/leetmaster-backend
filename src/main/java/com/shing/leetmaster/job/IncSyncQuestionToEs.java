package com.shing.leetmaster.job;


import cn.hutool.core.collection.CollUtil;
import com.shing.leetmaster.esdao.QuestionEsDao;
import com.shing.leetmaster.mapper.QuestionMapper;
import com.shing.leetmaster.model.dto.question.QuestionEsDTO;
import com.shing.leetmaster.model.entity.Question;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 增量同步题目到 es
 * @author Shing
 */
@Slf4j
public class IncSyncQuestionToEs {
    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionEsDao questionEsDao;

    /**
     * 每分钟执行一次
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void run() {
        // 查询近 5 分钟内的数据
        long FIVE_MINUTES = 5 * 60 * 1000L;
        Date fiveMinutesAgoDate = new Date(new Date().getTime() - FIVE_MINUTES);
        List<Question> questionList = questionMapper.listQuestionWithDelete(fiveMinutesAgoDate);
        if (CollUtil.isEmpty(questionList)) {
           log.info("IncSyncQuestionToEs no data, time {}", fiveMinutesAgoDate);
            return;
        }
        List<QuestionEsDTO> questionEsDTOList = questionList.stream()
                .map(QuestionEsDTO::objToDto)
                .collect(Collectors.toList());
        final int pageSize = 500;
        int total = questionEsDTOList.size();
        log.info("IncSyncQuestionToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            questionEsDao.saveAll(questionEsDTOList.subList(i, end));
        }
        log.info("IncSyncQuestionToEs end, total {}", total);
    }
}