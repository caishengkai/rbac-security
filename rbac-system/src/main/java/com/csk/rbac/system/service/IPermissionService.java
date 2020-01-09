package com.csk.rbac.system.service;

import com.csk.rbac.common.service.IBaseService;
import com.csk.rbac.system.model.Permission;

public interface IPermissionService extends IBaseService<Permission> {
    String findUserPermissions(Long userId);
}
