package com.community.subject.domain.handler.subject.impl;

import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.common.enums.SubjectInfoTypeEnum;
import com.community.subject.domain.convert.SubjectConverter;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.domain.handler.subject.SubjectTypeHandler;
import com.community.subject.infra.basic.entity.SubjectBrief;
import com.community.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SubjectBriefTypeHandlerImpl implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF ;
    }

    @Override
    public Boolean add(SubjectBO subjectBO) {
        SubjectBrief subjectBrief = SubjectConverter.INSTANCE.convertBOToSubjectBrief(subjectBO);
        subjectBrief.setSubjectId(subjectBO.getId());
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

        return subjectBriefService.insert(subjectBrief)>0;
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
