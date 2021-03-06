package com.csk.rbac.system.dao;

import com.csk.rbac.common.dao.BaseMapper;
import com.csk.rbac.system.model.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> findUserPermissions(Long userId);

    List<Permission> getUserMenu(String userName);
}