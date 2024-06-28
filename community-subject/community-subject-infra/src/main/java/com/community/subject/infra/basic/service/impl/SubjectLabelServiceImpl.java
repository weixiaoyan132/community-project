package com.community.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.community.subject.infra.basic.entity.SubjectLabel;
import com.community.subject.infra.basic.mapper.SubjectLabelDao;
import com.community.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务实现类
 *
 * @author makejava
 * @since 2024-06-26 02:55:59
 */
@Slf4j
@Service("subjectLabelService")
public class SubjectLabelServiceImpl implements SubjectLabelService {
    @Resource
    private SubjectLabelDao subjectLabelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLabel queryById(Long id) {
        return this.subjectLabelDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param subjectLabel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<SubjectLabel> queryByPage(SubjectLabel subjectLabel, PageRequest pageRequest) {
//        long total = this.subjectLabelDao.count(subjectLabel);
//        return new PageImpl<>(this.subjectLabelDao.queryAllByLimit(subjectLabel, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectLabel subjectLabel) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelServiceImpl.add.o:{}", JSON.toJSONString(subjectLabel));
        }

        return this.subjectLabelDao.insert(subjectLabel);
    }

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectLabel subjectLabel) {
        return this.subjectLabelDao.update(subjectLabel);
    }
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectLabelDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectLabel> batchQueryById(List<Long> labelList) {
        return this.subjectLabelDao.batchQueryById(labelList);
    }
}
