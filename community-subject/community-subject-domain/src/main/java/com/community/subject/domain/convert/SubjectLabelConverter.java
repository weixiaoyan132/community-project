package com.community.subject.domain.convert;

import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.domain.entity.SubjectLabelBO;
import com.community.subject.infra.basic.entity.SubjectCategory;
import com.community.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE= Mappers.getMapper(SubjectLabelConverter.class);
    SubjectLabel convertBOToLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertLabelListToBOList(List<SubjectLabel> SubjectLabelList);
}
