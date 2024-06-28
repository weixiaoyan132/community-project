package com.community.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.common.entity.PageResult;
import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.domain.convert.SubjectConverter;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.entity.SubjectOptionBO;
import com.community.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.community.subject.domain.service.SubjectDomainService;
import com.community.subject.infra.basic.entity.SubjectInfo;
import com.community.subject.infra.basic.entity.SubjectLabel;
import com.community.subject.infra.basic.entity.SubjectMapping;
import com.community.subject.infra.basic.service.SubjectInfoService;
import com.community.subject.infra.basic.service.SubjectLabelService;
import com.community.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.beans.Expression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectDomainServiceImpl implements SubjectDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SubjectBO subjectBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectBO));
        }
        //题目主表
        SubjectInfo subjectInfo= SubjectConverter.INSTANCE.convertBOToSubjectInfo(subjectBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        subjectBO.setId(subjectInfo.getId());

        //根据类型使用工厂策略,Bo中才有选项内容，info中没有所以传入BO
        subjectTypeHandlerFactory.getHandlerBySubjectInfoType(subjectBO.getSubjectType()).add(subjectBO);
        /*
        添加标签与类别的mapping表,分类与标签是多对多关系
         */
        List<Integer> subjectCategoryIds=subjectBO.getCategoryIds();
        List<Integer> subjectLabelIds=subjectBO.getLabelIds();
        List<SubjectMapping> subjectMappingList=new LinkedList<>();
        subjectCategoryIds.forEach(category->{
            subjectLabelIds.forEach(label->{
                SubjectMapping subjectMapping=new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(category));
                subjectMapping.setLabelId(Long.valueOf(label));
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectMappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappingList);
        return true;
    }

    @Override
    public PageResult<SubjectBO> querySubjectPage(SubjectBO subjectBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectPage.bo:{}", JSON.toJSONString(subjectBO));
        }
        PageResult<SubjectBO> boPageResult=new PageResult<>();
        boPageResult.setPageNo(subjectBO.getPageNo());
        boPageResult.setPageSize(subjectBO.getPageSize());
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectPage.boPageResult:{}", JSON.toJSONString(boPageResult));
        }
        SubjectInfo subjectInfo=SubjectConverter.INSTANCE.convertBOToSubjectInfo(subjectBO);
        int count=subjectInfoService.countByCondition(subjectInfo,subjectBO.getCategoryId(),subjectBO.getLabelId());
        if(count==0)
            return boPageResult;
        boPageResult.setTotal(count);
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectPage.boPageResult:{}", JSON.toJSONString(boPageResult));
        }
        List<SubjectInfo> subjectInfoList= subjectInfoService.queryPageByCondition(subjectInfo,
                subjectBO.getCategoryId(),
                subjectBO.getLabelId(),
                boPageResult.getStart(),
                boPageResult.getPageSize());
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectPage.subjectInfoList:{}", JSON.toJSONString(subjectInfoList));
        }
        List<SubjectBO> subjectBOList=SubjectConverter.INSTANCE.convertSubjectInfoListToBOList(subjectInfoList);
        boPageResult.setRecords(subjectBOList);
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectPage.boPageResult:{}", JSON.toJSONString(boPageResult));
        }
        return boPageResult;
    }

    @Override
    public SubjectBO querySubjectInfo(SubjectBO subjectBO) {
        SubjectInfo subjectInfo=subjectInfoService.queryById(subjectBO.getId());
        SubjectOptionBO OptionboResult=subjectTypeHandlerFactory.getHandlerBySubjectInfoType(subjectInfo.getSubjectType()).queryAnswer(subjectBO.getId());
        SubjectBO boResult=SubjectConverter.INSTANCE.convertSubjectAndOptionToSubjectBO(subjectInfo,OptionboResult);
        SubjectMapping subjectMapping=new SubjectMapping();
        subjectMapping.setSubjectId(subjectBO.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectInfo.subjectMapping:{}", JSON.toJSONString(subjectMapping));
        }
        List<SubjectMapping> subjectMappingList=subjectMappingService.queryDistinctLabelId(subjectMapping);
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectInfo.subjectMappingList:{}", JSON.toJSONString(subjectMappingList));
        }
        if(subjectMappingList.size()==0){
            boResult.setLabelNames(Collections.emptyList());
            return boResult;
        }
        List<Long> LabelList=subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectInfo.LabelList:{}", JSON.toJSONString(LabelList));
        }
        List<SubjectLabel> subjectLabelList=subjectLabelService.batchQueryById(LabelList);
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.querySubjectInfo.subjectLabelList:{}", JSON.toJSONString(subjectLabelList));
        }
        List<String> labelNameList = subjectLabelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        boResult.setLabelNames(labelNameList);
        return boResult;
    }
}
