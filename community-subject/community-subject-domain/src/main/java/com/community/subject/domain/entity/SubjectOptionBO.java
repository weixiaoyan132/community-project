package com.community.subject.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class SubjectOptionBO {
    private String subjectAnswer;

    private List<SubjectAnswerBO> optionList;
}
