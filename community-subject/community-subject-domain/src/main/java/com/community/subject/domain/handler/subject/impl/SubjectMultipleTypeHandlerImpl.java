package com.community.subject.domain.handler.subject.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.common.enums.SubjectInfoTypeEnum;
import com.community.subject.domain.convert.SubjectAnswerConverter;
import com.community.subject.domain.entity.SubjectAnswerBO;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.domain.handler.subject.SubjectTypeHandler;
import com.community.subject.infra.basic.entity.SubjectMultiple;
import com.community.subject.infra.basic.service.SubjectMultipleService;
import com.community.subject.infra.basic.service.impl.SubjectRadioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class SubjectMultipleTypeHandlerImpl implements SubjectTypeHandler {
    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE ;
    }

    /*
    单选题目的插入
     */
    @Override
    public Boolean add(SubjectBO subjectBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectRadioTypeHandlerImpl.add.bo:{}", JSON.toJSONString(subjectBO));
        }
        //多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = SubjectAnswerConverter.INSTANCE.convertBOToMultiple(option);
            subjectMultiple.setSubjectId(subjectBO.getId());
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectMultipleList.add(subjectMultiple);
        });

//        subjectRadioService.add
        return subjectMultipleService.batchInsert(subjectMultipleList)>0;
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId) {

        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerConverter.INSTANCE.convertMultipleListToBOList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
