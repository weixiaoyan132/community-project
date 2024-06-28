package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectAnswerDTO;
import com.community.subject.application.dto.SubjectDTO;
import com.community.subject.domain.entity.SubjectAnswerBO;
import com.community.subject.domain.entity.SubjectBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T16:41:22+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectDTOConverterImpl implements SubjectDTOConverter {

    @Override
    public SubjectBO convertDOToSubjectBO(SubjectDTO subjectDTO) {
        if ( subjectDTO == null ) {
            return null;
        }

        SubjectBO subjectBO = new SubjectBO();

        subjectBO.setPageNo( subjectDTO.getPageNo() );
        subjectBO.setPageSize( subjectDTO.getPageSize() );
        subjectBO.setId( subjectDTO.getId() );
        subjectBO.setSubjectName( subjectDTO.getSubjectName() );
        subjectBO.setSubjectDifficult( subjectDTO.getSubjectDifficult() );
        subjectBO.setSettleName( subjectDTO.getSettleName() );
        subjectBO.setSubjectType( subjectDTO.getSubjectType() );
        subjectBO.setSubjectScore( subjectDTO.getSubjectScore() );
        subjectBO.setSubjectParse( subjectDTO.getSubjectParse() );
        subjectBO.setSubjectAnswer( subjectDTO.getSubjectAnswer() );
        List<Integer> list = subjectDTO.getCategoryIds();
        if ( list != null ) {
            subjectBO.setCategoryIds( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = subjectDTO.getLabelIds();
        if ( list1 != null ) {
            subjectBO.setLabelIds( new ArrayList<Integer>( list1 ) );
        }
        List<String> list2 = subjectDTO.getLabelNames();
        if ( list2 != null ) {
            subjectBO.setLabelNames( new ArrayList<String>( list2 ) );
        }
        subjectBO.setOptionList( subjectAnswerDTOListToSubjectAnswerBOList( subjectDTO.getOptionList() ) );
        subjectBO.setCategoryId( subjectDTO.getCategoryId() );
        subjectBO.setLabelId( subjectDTO.getLabelId() );

        return subjectBO;
    }

    @Override
    public SubjectDTO convertBOToSubjectDTO(SubjectBO subjectBO) {
        if ( subjectBO == null ) {
            return null;
        }

        SubjectDTO subjectDTO = new SubjectDTO();

        subjectDTO.setPageNo( subjectBO.getPageNo() );
        subjectDTO.setPageSize( subjectBO.getPageSize() );
        subjectDTO.setId( subjectBO.getId() );
        subjectDTO.setSubjectName( subjectBO.getSubjectName() );
        subjectDTO.setSubjectDifficult( subjectBO.getSubjectDifficult() );
        subjectDTO.setSettleName( subjectBO.getSettleName() );
        subjectDTO.setSubjectType( subjectBO.getSubjectType() );
        subjectDTO.setSubjectScore( subjectBO.getSubjectScore() );
        subjectDTO.setSubjectParse( subjectBO.getSubjectParse() );
        subjectDTO.setSubjectAnswer( subjectBO.getSubjectAnswer() );
        List<Integer> list = subjectBO.getCategoryIds();
        if ( list != null ) {
            subjectDTO.setCategoryIds( new ArrayList<Integer>( list ) );
        }
        List<Integer> list1 = subjectBO.getLabelIds();
        if ( list1 != null ) {
            subjectDTO.setLabelIds( new ArrayList<Integer>( list1 ) );
        }
        List<String> list2 = subjectBO.getLabelNames();
        if ( list2 != null ) {
            subjectDTO.setLabelNames( new ArrayList<String>( list2 ) );
        }
        subjectDTO.setOptionList( subjectAnswerBOListToSubjectAnswerDTOList( subjectBO.getOptionList() ) );
        subjectDTO.setCategoryId( subjectBO.getCategoryId() );
        subjectDTO.setLabelId( subjectBO.getLabelId() );

        return subjectDTO;
    }

    @Override
    public List<SubjectDTO> convertSubjectBOListToDTOList(List<SubjectBO> SubjectBOList) {
        if ( SubjectBOList == null ) {
            return null;
        }

        List<SubjectDTO> list = new ArrayList<SubjectDTO>( SubjectBOList.size() );
        for ( SubjectBO subjectBO : SubjectBOList ) {
            list.add( convertBOToSubjectDTO( subjectBO ) );
        }

        return list;
    }

    protected SubjectAnswerBO subjectAnswerDTOToSubjectAnswerBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    protected List<SubjectAnswerBO> subjectAnswerDTOListToSubjectAnswerBOList(List<SubjectAnswerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerBO> list1 = new ArrayList<SubjectAnswerBO>( list.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : list ) {
            list1.add( subjectAnswerDTOToSubjectAnswerBO( subjectAnswerDTO ) );
        }

        return list1;
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

    protected List<SubjectAnswerDTO> subjectAnswerBOListToSubjectAnswerDTOList(List<SubjectAnswerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerDTO> list1 = new ArrayList<SubjectAnswerDTO>( list.size() );
        for ( SubjectAnswerBO subjectAnswerBO : list ) {
            list1.add( subjectAnswerBOToSubjectAnswerDTO( subjectAnswerBO ) );
        }

        return list1;
    }
}
