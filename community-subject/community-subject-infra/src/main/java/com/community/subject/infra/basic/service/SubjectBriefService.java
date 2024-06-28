package com.community.subject.infra.basic.service;

import com.community.subject.infra.basic.entity.SubjectBrief;


/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author makejava
 * @since 2024-06-27 01:02:03
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 分页查询
     *
     * @param subjectBrief 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    Page<SubjectBrief> queryByPage(SubjectBrief subjectBrief, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    int insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    SubjectBrief queryByCondition(SubjectBrief subjectBrief);
}
