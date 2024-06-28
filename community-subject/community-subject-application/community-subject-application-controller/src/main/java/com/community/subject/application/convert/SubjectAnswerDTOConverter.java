package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectAnswerDTO;
import com.community.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE= Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDOToSubjectAnswerBO(SubjectAnswerDTO subjectAnswerDTO);

    public List<SubjectAnswerBO> convertSubjectAnswerDTOListToBOList(List<SubjectAnswerDTO> SubjectAnswerDTOList);
    List<SubjectAnswerDTO> convertSubjectAnswerBOListToDTOList(List<SubjectAnswerBO> SubjectAnswerBOList);
}
