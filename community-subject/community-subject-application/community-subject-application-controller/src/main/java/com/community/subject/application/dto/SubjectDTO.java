package com.community.subject.application.dto;

import com.community.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectDTO extends PageInfo implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 题目大类
     */
    private List<Integer> categoryIds;
    /**
     * 题目标签
     */
    private List<Integer> LabelIds;
    /**
     * 题目标签名称
     */
    private List<String> LabelNames;
    /**
     * 答案选项
     */
    private List<SubjectAnswerDTO> optionList;

    private Long categoryId;

    private Long labelId;

}
