package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectDTO;
import com.community.subject.domain.entity.SubjectBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SubjectDTOConverter {
    SubjectDTOConverter INSTANCE= Mappers.getMapper(SubjectDTOConverter.class);
    SubjectBO convertDOToSubjectBO(SubjectDTO subjectDTO);
    SubjectDTO convertBOToSubjectDTO(SubjectBO subjectBO);
    List<SubjectDTO> convertSubjectBOListToDTOList(List<SubjectBO> SubjectBOList);
}
