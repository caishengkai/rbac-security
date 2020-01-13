package com.csk.rbac.system.service.impl;

import com.csk.rbac.common.RbacTree;
import com.csk.rbac.common.service.impl.BaseServiceImpl;
import com.csk.rbac.common.utils.TreeUtil;
import com.csk.rbac.system.dao.PermissionMapper;
import com.csk.rbac.system.model.Permission;
import com.csk.rbac.system.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<String> list = permissionMapper.findUserPermissions(userId);
        return list.stream().collect(Collectors.joining(","));
    }

    @Override
    public RbacTree<Permission> getUserMenu(String userName) {
        List<Permission> menus = permissionMapper.getUserMenu(userName);
        List<RbacTree<Permission>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            RbacTree<Permission> tree = new RbacTree<>();
            tree.setId(menu.getPermissionId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getPermissionName());
            tree.setIcon(menu.getIcon());
            tree.setUrl(menu.getUrl());
            trees.add(tree);
        });
        return TreeUtil.build(trees);
    }
}
