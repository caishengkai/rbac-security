package com.csk.rbac.system.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_role_permission")
public class RolePermission {
    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 菜单/按钮ID
     */
    @Column(name = "PERMISSION_ID")
    private Long permissionId;

    /**
     * 获取角色ID
     *
     * @return ROLE_ID - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单/按钮ID
     *
     * @return PERMISSION_ID - 菜单/按钮ID
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置菜单/按钮ID
     *
     * @param permissionId 菜单/按钮ID
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}