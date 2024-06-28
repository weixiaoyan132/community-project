package com.community.subject.domain.handler.subject.impl;

import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.common.enums.SubjectInfoTypeEnum;
import com.community.subject.domain.convert.SubjectAnswerConverter;
import com.community.subject.domain.entity.SubjectAnswerBO;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.domain.handler.subject.SubjectTypeHandler;
import com.community.subject.infra.basic.entity.SubjectJudge;
import com.community.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SubjectJudgeTypeHandlerImpl implements SubjectTypeHandler {

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public Boolean add(SubjectBO subjectBO) {
        //判断题目的插入
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

        return subjectJudgeService.insert(subjectJudge)>0;
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(Long.valueOf(subjectId));
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerConverter.INSTANCE.convertJudgeListToBOList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
