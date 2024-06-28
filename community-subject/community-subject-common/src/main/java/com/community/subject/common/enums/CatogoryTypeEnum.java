package com.community.subject.common.enums;

public enum CatogoryTypeEnum {
    /**
     * 分类类型枚举
     */
    PRIMARY(1,"岗位大类"),
    SECOND(0,"未二级分类");

    public int code;
    public String desc;

    CatogoryTypeEnum(int code, String desc) {
        this.code=code;
        this.desc=desc;
    }

    public static CatogoryTypeEnum getByCode(int codeVal){
        for (CatogoryTypeEnum catogoryTypeEnum:CatogoryTypeEnum.values()){
            if(catogoryTypeEnum.code==codeVal){
                return catogoryTypeEnum;
            }
        }
        return null;
    }
}
