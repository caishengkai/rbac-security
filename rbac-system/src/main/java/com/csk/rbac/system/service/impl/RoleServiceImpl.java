package com.csk.rbac.system.service.impl;

import com.csk.rbac.common.service.impl.BaseServiceImpl;
import com.csk.rbac.system.model.Role;
import com.csk.rbac.system.service.IRoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description:
 * @author: caishengkai
 * @time: 2020/1/13 15:58
 **/
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Override
    public List<Role> findAllRole(Role role) {
        Example example = new Example(Role.class);
        example.setOrderByClause("create_time");
        return this.selectByExample(example);
    }
}
