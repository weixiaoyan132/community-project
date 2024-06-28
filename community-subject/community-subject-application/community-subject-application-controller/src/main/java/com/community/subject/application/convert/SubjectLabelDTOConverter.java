package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectLabelDTO;
import com.community.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE= Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDOToLabelBO(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> convertLabelBOListToDTOList(List<SubjectLabelBO> SubjectLabelBOList);
}
