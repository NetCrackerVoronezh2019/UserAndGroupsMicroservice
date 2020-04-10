package ru.dto;

import ru.domen.Role;

public class RoleDTO {
    private Long roleId;
    private String roleName;

    public static RoleDTO getRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.roleId = role.getRoleId();
        roleDTO.roleName = role.getRoleName();
        return roleDTO;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
