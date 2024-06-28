package com.community.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.infra.basic.entity.SubjectMapping;
import com.community.subject.infra.basic.mapper.SubjectMappingDao;
import com.community.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-06-26 22:04:25
 */
@Service("subjectMappingService")
@Slf4j
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Long id) {
        return this.subjectMappingDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param subjectMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<SubjectMapping> queryByPage(SubjectMapping subjectMapping, PageRequest pageRequest) {
//        long total = this.subjectMappingDao.count(subjectMapping);
//        return new PageImpl<>(this.subjectMappingDao.queryAllByLimit(subjectMapping, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectMapping subjectMapping) {
        return this.subjectMappingDao.insert(subjectMapping);
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping update(SubjectMapping subjectMapping) {
        this.subjectMappingDao.update(subjectMapping);
        return this.queryById(subjectMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMappingDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectMapping> queryDistinctLabelId(SubjectMapping subjectMapping) {
        if(log.isInfoEnabled()){
            log.info("SubjectDomainServiceImpl.queryDistinctLabelId.subjectMapping:{}", JSON.toJSONString(subjectMapping));
        }
        return this.subjectMappingDao.queryDistinctLabelId(subjectMapping);
    }

    @Override
    public int batchInsert(List<SubjectMapping> subjectMappingList) {
        return this.subjectMappingDao.insertBatch(subjectMappingList);
    }
}
