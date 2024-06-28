package com.community.subject.common.entity;

import lombok.Data;

/**
 * 分页请求实体
 */
@Data
public class PageInfo {
    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo(){
        if(pageNo==null||pageNo<1)
            return 1;
        return pageNo;
    }

    public Integer getPageSize(){
        if(pageSize==null||pageSize<1||pageSize>Integer.MAX_VALUE){
            return 20;
        }
        return pageSize;
    }
}
