package com.community.subject.domain.handler.subject;

import com.community.subject.common.enums.SubjectInfoTypeEnum;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {
    /*
    枚举身份的识别
     */
    SubjectInfoTypeEnum getHandlerType();

    Boolean add(SubjectBO subjectBO);

    SubjectOptionBO queryAnswer(Long subjectId);
}
