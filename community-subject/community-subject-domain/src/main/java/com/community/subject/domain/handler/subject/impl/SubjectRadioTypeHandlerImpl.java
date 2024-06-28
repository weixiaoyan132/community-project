package com.community.subject.domain.handler.subject.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.common.enums.SubjectInfoTypeEnum;
import com.community.subject.domain.convert.SubjectAnswerConverter;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.domain.handler.subject.SubjectTypeHandler;
import com.community.subject.infra.basic.entity.SubjectRadio;
import com.community.subject.infra.basic.service.SubjectRadioService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 单选题目的策略类
 */
@Slf4j
@Component
public class SubjectRadioTypeHandlerImpl implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO ;
    }

    /*
    单选题目的插入
     */
    @Override
    public Boolean add(SubjectBO subjectBO) {
            if(log.isInfoEnabled()){
                log.info("SubjectRadioTypeHandlerImpl.add.bo:{}", JSON.toJSONString(subjectBO));
            }
        /*
        将bo转换为redio
         */
            List<SubjectRadio> subjectRadioList=new LinkedList<>();
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectBO.getCategoryIds()), "单选题选项不能为空");
            subjectBO.getOptionList().forEach(option->{
                SubjectRadio subjectRadio= SubjectAnswerConverter.INSTANCE.convertBOToRadio(option);
                subjectRadio.setSubjectId(subjectBO.getId());
                subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectRadioList.add(subjectRadio);
            });
            return subjectRadioService.batchInsert(subjectRadioList)>0;
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId) {
        SubjectRadio subjectRadio=new SubjectRadio();
        subjectRadio.setSubjectId(subjectId);
        List<SubjectRadio> subjectRadioList=subjectRadioService.queryByCondition(subjectRadio);
        SubjectOptionBO subjectOptionBO=new SubjectOptionBO();
        subjectOptionBO.setOptionList(SubjectAnswerConverter.INSTANCE.convertRadioListToBOList(subjectRadioList));
        return subjectOptionBO;
    }
}
