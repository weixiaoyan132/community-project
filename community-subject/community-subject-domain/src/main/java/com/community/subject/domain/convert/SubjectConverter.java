package com.community.subject.domain.convert;

import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.infra.basic.entity.SubjectBrief;
import com.community.subject.infra.basic.entity.SubjectCategory;
import com.community.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SubjectConverter {
    SubjectConverter INSTANCE= Mappers.getMapper(SubjectConverter.class);

    SubjectInfo convertBOToSubjectInfo(SubjectBO subjectBO);

    SubjectBO convertSubjectAndOptionToSubjectBO(SubjectInfo subjectInfo, SubjectOptionBO subjectOptionBO);

    List<SubjectBO> convertSubjectInfoListToBOList(List<SubjectInfo> SubjectInfoList);

    SubjectBrief convertBOToSubjectBrief(SubjectBO subjectBO);
}
