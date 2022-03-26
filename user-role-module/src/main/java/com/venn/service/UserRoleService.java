package com.venn.service;

import com.venn.po.Role;
import com.venn.po.User;

import java.util.List;


public interface UserRoleService {

    /**
     * Add a existing role to user
     * @param user
     * @param role
     * @return true: successfully add role to user
     */
    public boolean addRole(User user, Role role);

    /**
     * if the username and pwd is right, return a token
     * @param Username
     * @param password
     * @return token
     */
    public String authenticateUser(String Username, String password);


    /**
     * check if user has this role, should be identified by the token
     * @param user
     * @param role
     * @param token
     * @return
     */
    public boolean checkUserRole(User user, Role role, String token);

    /**
     * Get all roles owned by user
     * @param user
     * @param token
     * @return roleList owned by user, if user invalid or token invalid, return null
     */
    public List<Role> getAllRolesByUser(User user, String token);
}
