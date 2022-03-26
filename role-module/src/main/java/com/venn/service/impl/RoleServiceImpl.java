package com.venn.service.impl;

import com.venn.constants.StaticConstants;
import com.venn.po.Role;
import com.venn.service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static List<Role> roleList = new ArrayList<Role>();

    @Override
    public boolean createRole(String roleName) {
        if (null != roleName && !StaticConstants.EMPTY_STR.equals(roleName.trim())) {
            Role role = new Role(roleName);
            if (!roleList.contains(role)) {
                synchronized (roleList) {
                    if (!roleList.contains(role)) {
                        roleList.add(role);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteRole(Role role) {
        if (null != role && null != role.getRoleName()
                && !StaticConstants.EMPTY_STR.equals(role.getRoleName().trim())
                && !roleList.isEmpty() && roleList.contains(role)) {
            synchronized (roleList) {
                if (roleList.contains(role)) {
                    roleList.remove(role);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean isValidate(Role role) {
        return roleList.contains(role);
    }
}
