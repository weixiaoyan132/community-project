package com.community.subject.infra.basic.service;

import com.community.subject.infra.basic.entity.SubjectInfo;

import java.util.List;


/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-06-27 00:55:00
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);

    /**
     * 分页查询
     *
     * @param subjectInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    Page<SubjectInfo> queryByPage(SubjectInfo subjectInfo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    int insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);

    List<SubjectInfo> queryPageByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId, Integer start, Integer pageSize);
}
