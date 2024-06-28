package com.community.subject.domain.convert;

import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConverter {
    SubjectCategoryConverter INSTANCE= Mappers.getMapper(SubjectCategoryConverter.class);
    SubjectCategory convertBOToCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertCategoryListToBOList(List<SubjectCategory> SubjectCategoryList);
}
