package com.community.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.common.enums.IsDeletedFlagEnum;
import com.community.subject.domain.convert.SubjectCategoryConverter;
import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.domain.service.SubjectCategoryDomainService;
import com.community.subject.infra.basic.entity.SubjectCategory;
import com.community.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;

    public void add(SubjectCategoryBO subjectCategoryBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory=SubjectCategoryConverter.INSTANCE.convertBOToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //insert函数的参数为subjectCategory，而领域层就是为了有自己独立的处理subjectCategoryBO，每个领域分开，
        //所以如何将subjectCategoryBO转化为insert函数的subjectCategory？
        //用领域拦截器？
        subjectCategoryService.insert(subjectCategory);
    }

    public List<SubjectCategoryBO> queryCategoryList(SubjectCategoryBO subjectCategoryBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.CategoryListByPrimary.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory=SubjectCategoryConverter.INSTANCE.convertBOToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList=subjectCategoryService.queryCategoryList(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList=SubjectCategoryConverter.INSTANCE.convertCategoryListToBOList(subjectCategoryList);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.PrimaryCategoryList.boList:{}", JSON.toJSONString(subjectCategoryBOList));
        }
        return subjectCategoryBOList;
    }

    public Boolean updateCategory(SubjectCategoryBO subjectCategoryBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.updateCategory.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory=SubjectCategoryConverter.INSTANCE.convertBOToCategory(subjectCategoryBO);
        int update=subjectCategoryService.update(subjectCategory);
        return update>0;
    }

    public Boolean deleteCategory(SubjectCategoryBO subjectCategoryBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.deleteCategory.bo:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory=SubjectCategoryConverter.INSTANCE.convertBOToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int update=subjectCategoryService.update(subjectCategory);
        return update>0;
    }
}
