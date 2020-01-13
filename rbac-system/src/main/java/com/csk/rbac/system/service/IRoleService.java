package com.csk.rbac.system.service;

import com.csk.rbac.common.service.IBaseService;
import com.csk.rbac.system.model.Role;

import java.util.List;

/**
 * @description:
 * @author: caishengkai
 * @time: 2020/1/13 15:57
 **/
public interface IRoleService extends IBaseService<Role> {
    List<Role> findAllRole(Role role);
}
