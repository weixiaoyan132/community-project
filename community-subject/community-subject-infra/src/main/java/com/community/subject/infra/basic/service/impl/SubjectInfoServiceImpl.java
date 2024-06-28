package com.community.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.infra.basic.entity.SubjectInfo;
import com.community.subject.infra.basic.mapper.SubjectInfoDao;
import com.community.subject.infra.basic.service.SubjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-06-27 00:55:00
 */
@Slf4j
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectInfo queryById(Long id) {
        return this.subjectInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param subjectInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<SubjectInfo> queryByPage(SubjectInfo subjectInfo, PageRequest pageRequest) {
//        long total = this.subjectInfoDao.count(subjectInfo);
//        return new PageImpl<>(this.subjectInfoDao.queryAllByLimit(subjectInfo, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectInfo subjectInfo) {
        if(log.isInfoEnabled()){
            log.info("SubjectInfoServiceImpl.add.o:{}", JSON.toJSONString(subjectInfo));
        }
        return this.subjectInfoDao.insert(subjectInfo);
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo update(SubjectInfo subjectInfo) {
        this.subjectInfoDao.update(subjectInfo);
        return this.queryById(subjectInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectInfoDao.deleteById(id) > 0;
    }

    @Override
    public int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId) {
        return this.subjectInfoDao.countByCondition( subjectInfo,  categoryId,  labelId);
    }

    @Override
    public List<SubjectInfo> queryPageByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId, Integer start, Integer pageSize) {
        return this.subjectInfoDao.queryPageByCondition(subjectInfo, categoryId, labelId,start,pageSize);
    }
}
