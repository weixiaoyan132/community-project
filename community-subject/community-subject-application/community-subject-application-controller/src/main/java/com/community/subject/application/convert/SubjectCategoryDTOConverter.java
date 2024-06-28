package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectCategoryDTO;
import com.community.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE= Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertCategoryBOListToDTOList(List<SubjectCategoryBO> SubjectCategoryBOList);
}
