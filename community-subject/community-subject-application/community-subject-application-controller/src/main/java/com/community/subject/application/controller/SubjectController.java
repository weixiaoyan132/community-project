package com.community.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.community.subject.application.convert.SubjectAnswerDTOConverter;
import com.community.subject.application.convert.SubjectDTOConverter;
import com.community.subject.application.dto.SubjectDTO;
import com.community.subject.common.entity.PageResult;
import com.community.subject.common.entity.Result;
import com.community.subject.domain.entity.SubjectAnswerBO;
import com.community.subject.domain.entity.SubjectBO;
import com.community.subject.domain.service.SubjectDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectDomainService subjectDomainService;

    /**
     * 新增题目
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectDTO subjectDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(subjectDTO.getSubjectName()),"题目名称不能为空");
            Preconditions.checkNotNull(subjectDTO.getSubjectType(),"题目类型不能为空");
            Preconditions.checkNotNull(subjectDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectDTO.getCategoryIds()), "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectDTO.getLabelIds()), "标签id不能为空");
            SubjectBO subjectBO=SubjectDTOConverter.INSTANCE.convertDOToSubjectBO(subjectDTO);
            List<SubjectAnswerBO> subjectAnswerBOS =
                    SubjectAnswerDTOConverter.INSTANCE.convertSubjectAnswerDTOListToBOList(subjectDTO.getOptionList());
            subjectBO.setOptionList(subjectAnswerBOS);
            Boolean result= subjectDomainService.add(subjectBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectController.add.error:{}",e.getMessage(),e);
            return Result.fail("添加失败");
        }

    }

    /**
     * 查询题目列表
     */
    @PostMapping("/querySubjectPage")
    public Result<PageResult<SubjectDTO>> querySubjectPage(@RequestBody SubjectDTO subjectDTO ){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectController.querySubjectPage.subjectDTO:{}", JSON.toJSONString(subjectDTO));
            }
            Preconditions.checkNotNull(subjectDTO.getCategoryId(),"查询的题目类型不能为空");
            Preconditions.checkNotNull(subjectDTO.getLabelId(), "查询的标签不能为空");
            SubjectBO subjectBO=SubjectDTOConverter.INSTANCE.convertDOToSubjectBO(subjectDTO);
            PageResult<SubjectBO> boresult= subjectDomainService.querySubjectPage(subjectBO);
            //把bo转为dto传出去？
//            PageResult<SubjectDTO> dtoPageResult=new PageResult<>();
//            dtoPageResult.setPageNo(boresult.getPageNo());
//            dtoPageResult.setPageSize(boresult.getPageSize());
//            dtoPageResult.setTotal(boresult.getTotal());
//            dtoPageResult.setRecords(SubjectDTOConverter.INSTANCE.convertSubjectBOListToDTOList(boresult.getResult()));
            return Result.ok(boresult);
        }catch (Exception e){
            log.error("SubjectController.querySubjectPage.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    @PostMapping("/querySubjectInfo")
    public Result<SubjectDTO> v(@RequestBody SubjectDTO subjectDTO ){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectController.querySubjectInfo.subjectDTO:{}", JSON.toJSONString(subjectDTO));
            }
            Preconditions.checkNotNull(subjectDTO.getId(),"查询的题目ID不能为空");
            SubjectBO subjectBO=SubjectDTOConverter.INSTANCE.convertDOToSubjectBO(subjectDTO);
            SubjectBO boResult= subjectDomainService.querySubjectInfo(subjectBO);
            SubjectDTO dtoResult = SubjectDTOConverter.INSTANCE.convertBOToSubjectDTO(boResult);
            return Result.ok(dtoResult);
        }catch (Exception e){
            log.error("SubjectController.querySubjectInfo.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

//    @GetMapping("/test")
//    public String test(){
//        SubjectCategory subjectCategory=subjectCategoryService.queryById(1L);
//        return subjectCategory.getCategoryName();
//    }




}
