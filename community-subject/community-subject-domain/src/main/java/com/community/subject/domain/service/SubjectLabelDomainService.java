package com.community.subject.domain.service;

import com.community.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectLabelDomainService {
    boolean add(SubjectLabelBO subjectLabelBO);

    Boolean updateLabel(SubjectLabelBO subjectLabelBO);

    Boolean deleteLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategory(SubjectLabelBO subjectLabelBO);
}
