package com.community.subject.domain.entity;

import lombok.Data;

@Data
public class SubjectAnswerBO {
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;
}
