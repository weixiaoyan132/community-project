package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectAnswerDTO;
import com.community.subject.domain.entity.SubjectAnswerBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T16:41:22+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public SubjectAnswerBO convertDOToSubjectAnswerBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    @Override
    public List<SubjectAnswerBO> convertSubjectAnswerDTOListToBOList(List<SubjectAnswerDTO> SubjectAnswerDTOList) {
        if ( SubjectAnswerDTOList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( SubjectAnswerDTOList.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : SubjectAnswerDTOList ) {
            list.add( convertDOToSubjectAnswerBO( subjectAnswerDTO ) );
        }

        return list;
    }

    @Override
    public List<SubjectAnswerDTO> convertSubjectAnswerBOListToDTOList(List<SubjectAnswerBO> SubjectAnswerBOList) {
        if ( SubjectAnswerBOList == null ) {
            return null;
        }

        List<SubjectAnswerDTO> list = new ArrayList<SubjectAnswerDTO>( SubjectAnswerBOList.size() );
        for ( SubjectAnswerBO subjectAnswerBO : SubjectAnswerBOList ) {
            list.add( subjectAnswerBOToSubjectAnswerDTO( subjectAnswerBO ) );
        }

        return list;
    }

    protected SubjectAnswerDTO subjectAnswerBOToSubjectAnswerDTO(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectAnswerDTO subjectAnswerDTO = new SubjectAnswerDTO();

        subjectAnswerDTO.setOptionType( subjectAnswerBO.getOptionType() );
        subjectAnswerDTO.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectAnswerDTO.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectAnswerDTO;
    }
}
