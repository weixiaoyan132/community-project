package com.community.subject.domain.service;

import com.community.subject.common.entity.PageResult;
import com.community.subject.domain.entity.SubjectBO;

public interface SubjectDomainService {
    Boolean add(SubjectBO subjectBO) throws Exception;

    PageResult<SubjectBO> querySubjectPage(SubjectBO subjectBO);

    SubjectBO querySubjectInfo(SubjectBO subjectBO);
}
