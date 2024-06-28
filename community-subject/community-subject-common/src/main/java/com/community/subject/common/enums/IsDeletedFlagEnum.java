package com.community.subject.common.enums;

import lombok.Getter;

@Getter
public enum IsDeletedFlagEnum {
    DELETED(1,"已删除"),
    UN_DELETED(0,"未删除");

    public int code;
    public String desc;

    IsDeletedFlagEnum(int code, String desc) {
        this.code=code;
        this.desc=desc;
    }

    public static IsDeletedFlagEnum getByCode(int codeVal){
        for (IsDeletedFlagEnum isDeletedFlagEnum:IsDeletedFlagEnum.values()){
            if(isDeletedFlagEnum.code==codeVal){
                return isDeletedFlagEnum;
            }
        }
        return null;
    }
}
