package com.csk.rbac.common.service.impl;

import com.csk.rbac.common.dao.BaseMapper;
import com.csk.rbac.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/30 14:39
 **/
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    protected BaseMapper<T> baseMapper;

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    public T selectByKey(Object key) {
        return baseMapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return baseMapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int batchDelete(List<String> list, String property, Class<T> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.baseMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public int updateAll(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional
    public int updateNotNull(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return baseMapper.selectByExample(example);
    }
}
