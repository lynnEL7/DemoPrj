package com.venn.service;

import com.venn.po.Role;

public interface RoleService {

    /**
     * @param roleName
     * @return true: role created, false: role exists
     */
    public boolean createRole(String roleName);

    /**
     * @param role
     * @return true: role deleted, false: role not exists
     */
    public boolean deleteRole(Role role);

    /**
     * @param role
     * @return true: role exists, false: no
     */
    public boolean isValidate(Role role);
}
