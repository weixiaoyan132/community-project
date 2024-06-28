package com.community.subject.domain.handler.subject;

import com.community.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目类型工厂
 *
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {
    /*
    存放了所有SubjectTypeHandler的实现类
     */
    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    private Map<SubjectInfoTypeEnum,SubjectTypeHandler> subjectTypeHandlerMap=new HashMap<>();

    public SubjectTypeHandler getHandlerBySubjectInfoType(int subjectInfoType){
        SubjectInfoTypeEnum subjectInfoTypeEnum=SubjectInfoTypeEnum.getByCode(subjectInfoType);

        return subjectTypeHandlerMap.get(subjectInfoTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler:subjectTypeHandlerList){
            subjectTypeHandlerMap.put(subjectTypeHandler.getHandlerType(),subjectTypeHandler);
        }
    }
}
