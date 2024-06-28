package com.community.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.community.subject.application.convert.SubjectLabelDTOConverter;
import com.community.subject.application.dto.SubjectLabelDTO;
import com.community.subject.common.entity.Result;
import com.community.subject.domain.entity.SubjectLabelBO;
import com.community.subject.domain.service.SubjectLabelDomainService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {
    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
//            Preconditions.checkNotNull(subjectLabelDTO.getSortNum(),"标签排名不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()),"分类名称不能为空");
            Boolean result= subjectLabelDomainService.add((SubjectLabelDTOConverter.INSTANCE.convertDOToLabelBO(subjectLabelDTO)));

            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.add.error:{}",e.getMessage(),e);
            return Result.fail("添加失败");
        }

    }

    /**
     * 更新标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> updateLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.updateLabel.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签ID不能为空");

            Boolean result= subjectLabelDomainService.updateLabel((SubjectLabelDTOConverter.INSTANCE.convertDOToLabelBO(subjectLabelDTO)));
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.updateLabel.error:{}",e.getMessage(),e);
            return Result.fail("更新失败");
        }

    }
    @PostMapping("/delete")
    public Result<Boolean> deleteLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.deleteLabel.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签ID不能为空");
            Boolean result= subjectLabelDomainService.deleteLabel((SubjectLabelDTOConverter.INSTANCE.convertDOToLabelBO(subjectLabelDTO)));
            if(result==false)
                return Result.ok("ID不存在删除错误");
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.deleteLabel.error:{}",e.getMessage(),e);
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/queryLabelByCategory")
    public Result<List<SubjectLabelDTO>> queryLabelByCategory(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.queryLabelByCategory.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类ID不能为空");
            List<SubjectLabelBO> subjectLabelBOList= subjectLabelDomainService.queryLabelByCategory((SubjectLabelDTOConverter.INSTANCE.convertDOToLabelBO(subjectLabelDTO)));
            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelDTOConverter.INSTANCE.convertLabelBOListToDTOList(subjectLabelBOList);
            return Result.ok(subjectLabelDTOList);
        }catch (Exception e){
            log.error("SubjectLabelController.queryLabelByCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询分类下标签失败");
        }
    }
}
