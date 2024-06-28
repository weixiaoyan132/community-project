package com.community.subject.domain.convert;

import com.community.subject.domain.entity.SubjectAnswerBO;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.infra.basic.entity.SubjectBrief;
import com.community.subject.infra.basic.entity.SubjectInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T19:23:57+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectConverterImpl implements SubjectConverter {

    @Override
    public SubjectInfo convertBOToSubjectInfo(SubjectBO subjectBO) {
        if ( subjectBO == null ) {
            return null;
        }

        SubjectInfo subjectInfo = new SubjectInfo();

        subjectInfo.setId( subjectBO.getId() );
        subjectInfo.setSubjectName( subjectBO.getSubjectName() );
        subjectInfo.setSubjectDifficult( subjectBO.getSubjectDifficult() );
        subjectInfo.setSettleName( subjectBO.getSettleName() );
        subjectInfo.setSubjectType( subjectBO.getSubjectType() );
        subjectInfo.setSubjectScore( subjectBO.getSubjectScore() );
        subjectInfo.setSubjectParse( subjectBO.getSubjectParse() );

        return subjectInfo;
    }

    @Override
    public SubjectBO convertSubjectAndOptionToSubjectBO(SubjectInfo subjectInfo, SubjectOptionBO subjectOptionBO) {
        if ( subjectInfo == null && subjectOptionBO == null ) {
            return null;
        }

        SubjectBO subjectBO = new SubjectBO();

        if ( subjectInfo != null ) {
            subjectBO.setId( subjectInfo.getId() );
            subjectBO.setSubjectName( subjectInfo.getSubjectName() );
            subjectBO.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
            subjectBO.setSettleName( subjectInfo.getSettleName() );
            subjectBO.setSubjectType( subjectInfo.getSubjectType() );
            subjectBO.setSubjectScore( subjectInfo.getSubjectScore() );
            subjectBO.setSubjectParse( subjectInfo.getSubjectParse() );
        }
        if ( subjectOptionBO != null ) {
            subjectBO.setSubjectAnswer( subjectOptionBO.getSubjectAnswer() );
            List<SubjectAnswerBO> list = subjectOptionBO.getOptionList();
            if ( list != null ) {
                subjectBO.setOptionList( new ArrayList<SubjectAnswerBO>( list ) );
            }
        }

        return subjectBO;
    }

    @Override
    public List<SubjectBO> convertSubjectInfoListToBOList(List<SubjectInfo> SubjectInfoList) {
        if ( SubjectInfoList == null ) {
            return null;
        }

        List<SubjectBO> list = new ArrayList<SubjectBO>( SubjectInfoList.size() );
        for ( SubjectInfo subjectInfo : SubjectInfoList ) {
            list.add( subjectInfoToSubjectBO( subjectInfo ) );
        }

        return list;
    }

    @Override
    public SubjectBrief convertBOToSubjectBrief(SubjectBO subjectBO) {
        if ( subjectBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectBO.getId() );
        subjectBrief.setSubjectAnswer( subjectBO.getSubjectAnswer() );

        return subjectBrief;
    }

    protected SubjectBO subjectInfoToSubjectBO(SubjectInfo subjectInfo) {
        if ( subjectInfo == null ) {
            return null;
        }

        SubjectBO subjectBO = new SubjectBO();

        subjectBO.setId( subjectInfo.getId() );
        subjectBO.setSubjectName( subjectInfo.getSubjectName() );
        subjectBO.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
        subjectBO.setSettleName( subjectInfo.getSettleName() );
        subjectBO.setSubjectType( subjectInfo.getSubjectType() );
        subjectBO.setSubjectScore( subjectInfo.getSubjectScore() );
        subjectBO.setSubjectParse( subjectInfo.getSubjectParse() );

        return subjectBO;
    }
}
