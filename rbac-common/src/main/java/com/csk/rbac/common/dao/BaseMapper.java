package com.csk.rbac.common.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/31 11:53
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
