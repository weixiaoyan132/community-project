package com.community.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.domain.convert.SubjectLabelConverter;
import com.community.subject.domain.entity.SubjectLabelBO;
import com.community.subject.domain.service.SubjectLabelDomainService;
import com.community.subject.infra.basic.entity.SubjectLabel;
import com.community.subject.infra.basic.entity.SubjectMapping;
import com.community.subject.infra.basic.service.SubjectLabelService;
import com.community.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public boolean add(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel= SubjectLabelConverter.INSTANCE.convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        return subjectLabelService.insert(subjectLabel)>0;
    }

    @Override
    public Boolean updateLabel(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.updateLabel.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel= SubjectLabelConverter.INSTANCE.convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public Boolean deleteLabel(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.deleteLabel.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel= SubjectLabelConverter.INSTANCE.convertBOToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategory(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.queryLabelByCategory.bo:{}", JSON.toJSONString(subjectLabelBO));
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping=new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        List<SubjectMapping> subjectMappingList=subjectMappingService.queryDistinctLabelId(subjectMapping);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.queryDistinctLabelId.subjectMappingList:{}", JSON.toJSONString(subjectMappingList));
        }
        if(CollectionUtils.isEmpty(subjectMappingList))
            return Collections.emptyList();
        List<Long> LabelList=subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.queryDistinctLabelId.LabelList:{}", JSON.toJSONString(LabelList));
        }
        List<SubjectLabel> subjectLabelList=subjectLabelService.batchQueryById(LabelList);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.queryDistinctLabelId.subjectLabelList:{}", JSON.toJSONString(subjectLabelList));
        }
        List<SubjectLabelBO> subjectLabelBOList=new ArrayList<>();
        subjectLabelList.forEach(label->{
            SubjectLabelBO LabelBO=new SubjectLabelBO();
            LabelBO.setId(label.getId());
            LabelBO.setLabelName(label.getLabelName());
            LabelBO.setCategoryId(label.getCategoryId());
            subjectLabelBOList.add(LabelBO);
        });
        return subjectLabelBOList;
    }
}
