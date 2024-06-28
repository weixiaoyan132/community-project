package com.community.subject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectInfoTypeEnum {
    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");

    public int code;
    public String desc;

    SubjectInfoTypeEnum(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public static SubjectInfoTypeEnum getByCode(int codeVal){
        for (SubjectInfoTypeEnum subjectInfoTypeEnum:SubjectInfoTypeEnum.values()){
            if(subjectInfoTypeEnum.code==codeVal){
                return subjectInfoTypeEnum;
            }
        }
        return null;
    }
}