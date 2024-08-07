package com.community.subject.application.convert;

import com.community.subject.application.dto.SubjectCategoryDTO;
import com.community.subject.domain.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T19:24:00+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBO convertDOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertCategoryBOListToDTOList(List<SubjectCategoryBO> SubjectCategoryBOList) {
        if ( SubjectCategoryBOList == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( SubjectCategoryBOList.size() );
        for ( SubjectCategoryBO subjectCategoryBO : SubjectCategoryBOList ) {
            list.add( subjectCategoryBOToSubjectCategoryDTO( subjectCategoryBO ) );
        }

        return list;
    }

    protected SubjectCategoryDTO subjectCategoryBOToSubjectCategoryDTO(SubjectCategoryBO subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( subjectCategoryBO.getId() );
        subjectCategoryDTO.setCategoryName( subjectCategoryBO.getCategoryName() );
        subjectCategoryDTO.setCategoryType( subjectCategoryBO.getCategoryType() );
        subjectCategoryDTO.setImageUrl( subjectCategoryBO.getImageUrl() );
        subjectCategoryDTO.setParentId( subjectCategoryBO.getParentId() );

        return subjectCategoryDTO;
    }
}
