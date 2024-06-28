package com.community.subject.domain.convert;

import com.community.subject.domain.entity.SubjectAnswerBO;
import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.infra.basic.entity.SubjectCategory;
import com.community.subject.infra.basic.entity.SubjectJudge;
import com.community.subject.infra.basic.entity.SubjectMultiple;
import com.community.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerConverter {
    SubjectAnswerConverter INSTANCE= Mappers.getMapper(SubjectAnswerConverter.class);
    SubjectRadio convertBOToRadio(SubjectAnswerBO subjectAnswerBO);
    SubjectMultiple convertBOToMultiple(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertRadioListToBOList(List<SubjectRadio> subjectRadioList);
    List<SubjectAnswerBO> convertMultipleListToBOList(List<SubjectMultiple> subjectMultipleList);
    List<SubjectAnswerBO> convertJudgeListToBOList(List<SubjectJudge> subjectJudgeList);
}
