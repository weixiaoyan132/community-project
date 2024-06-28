package com.community.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.community.subject.application.convert.SubjectCategoryDTOConverter;
import com.community.subject.application.dto.SubjectCategoryDTO;
import com.community.subject.common.entity.Result;
import com.community.subject.domain.entity.SubjectCategoryBO;
import com.community.subject.domain.service.SubjectCategoryDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {
    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"类型父类型不能为空");
            subjectCategoryDomainService.add((SubjectCategoryDTOConverter.INSTANCE.convertDOToCategoryBO(subjectCategoryDTO)));

            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }
    @PostMapping("/PrimaryCategoryList")
    public Result<List<SubjectCategoryDTO>> PrimaryCategoryList(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.CategoryListByPrimary.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类的分类不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDOToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList= subjectCategoryDomainService.queryCategoryList(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertCategoryBOListToDTOList(subjectCategoryBOList);
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.PrimaryCategoryList.DTOList:{}", JSON.toJSONString(subjectCategoryDTOList));
            }
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.PrimaryCategoryList.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }
    @PostMapping("/CategoryListByPrimary")
    public Result<List<SubjectCategoryDTO>> CategoryListByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.CategoryListByPrimary.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父类分类ID不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类的分类不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDOToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList= subjectCategoryDomainService.queryCategoryList(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertCategoryBOListToDTOList(subjectCategoryBOList);
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.CategoryListByPrimary.DTOList:{}", JSON.toJSONString(subjectCategoryDTOList));
            }
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.CategoryListByPrimary.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 更新分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> updateCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.updateCategory.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDOToCategoryBO(subjectCategoryDTO);
            return Result.ok(subjectCategoryDomainService.updateCategory(subjectCategoryBO));
        }catch (Exception e){
            log.error("SubjectCategoryController.updateCategory.error:{}",e.getMessage(),e);
            return Result.fail("修改失败");
        }
    }
    @PostMapping("/delete")
    public Result<Boolean> deleteCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.deleteCategory.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"删除的分类ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDOToCategoryBO(subjectCategoryDTO);
            return Result.ok(subjectCategoryDomainService.deleteCategory(subjectCategoryBO));
        }catch (Exception e){
            log.error("SubjectCategoryController.deleteCategory.error:{}",e.getMessage(),e);
            return Result.fail("删除分类失败");
        }
    }


}
