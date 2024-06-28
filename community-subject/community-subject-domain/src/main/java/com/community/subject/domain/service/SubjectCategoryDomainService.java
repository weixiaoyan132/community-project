package com.community.subject.domain.service;

import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.infra.basic.entity.SubjectCategory;
import com.community.subject.infra.basic.service.SubjectCategoryService;

import java.util.List;

public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);

    /*
    查询岗位大类
     */
    List<SubjectCategoryBO> queryCategoryList(SubjectCategoryBO subjectCategoryBO);


    Boolean updateCategory(SubjectCategoryBO subjectCategoryBO);

    Boolean deleteCategory(SubjectCategoryBO subjectCategoryBO);
}
