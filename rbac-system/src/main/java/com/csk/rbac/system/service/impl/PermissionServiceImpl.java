package com.csk.rbac.system.service.impl;

import com.csk.rbac.common.service.impl.BaseServiceImpl;
import com.csk.rbac.system.dao.PermissionMapper;
import com.csk.rbac.system.model.Permission;
import com.csk.rbac.system.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: caishengkai
 * @time: 2019/12/31 9:05
 **/
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public String findUserPermissions(Long userId) {
        List<Permission> list = permissionMapper.findUserPermissions(userId);
        return list.stream().map(Permission::getPerms).collect(Collectors.joining(","));
    }
}
