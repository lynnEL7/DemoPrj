package com.venn.service.impl;

import com.venn.constants.CommonUtil;
import com.venn.constants.StaticConstants;
import com.venn.po.Role;
import com.venn.po.User;
import com.venn.service.RoleService;
import com.venn.service.TokenService;
import com.venn.service.UserRoleService;
import com.venn.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRoleServiceImpl implements UserRoleService {

    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private TokenService tokenService = new TokenServiceImpl();
    public static Map<User, List<Role>> userRoles = new ConcurrentHashMap<User, List<Role>>();

    @Override
    public boolean addRole(User user, Role role) {
        if (null == user || null == role) {
            return false;
        }
        if (!isUserValidate(user) || !roleService.isValidate(role)) {
            return false;
        }
        if (userRoles.isEmpty() || !userRoles.containsKey(user)) {
            List<Role> roles = new ArrayList<Role>();
            roles.add(role);
            userRoles.put(user, roles);
        } else {
            List<Role> roles = userRoles.get(user);
            if (!roles.contains(role)) {
                userRoles.get(user).add(role);
//                maps.put(user, roles);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public String authenticateUser(String username, String password) {

        if(!userService.isValidate(username, password)){
            return null;
        }
        return tokenService.generateToken();
    }

    @Override
    public boolean checkUserRole(User user, Role role, String token) {
        if (null == user || null == role || null == token || StaticConstants.EMPTY_STR.equals(token.trim())) {
            return false;
        }
        if (!isUserValidate(user) || !roleService.isValidate(role)) {
            return false;
        }
        if(!tokenService.checkToken(token, StaticConstants.TOKEN_VALID_PERIOD)){
            return false;
        }
        return userRoles.get(user).contains(role);

    }

    private boolean isUserValidate(User user){
        String userPwd = User.getDecodedPassword(CommonUtil.decodePwd(user.getPassword()));
        return userService.isValidate(user.getUserName(), userPwd);
    }

    @Override
    public List<Role> getAllRolesByUser(User user, String token) {
        if (null == user || null == token || StaticConstants.EMPTY_STR.equals(token.trim())) {
            return null;
        }
        if (!isUserValidate(user) ) {
            return null;
        }
        if(!tokenService.checkToken(token, StaticConstants.TOKEN_VALID_PERIOD)){
            return null;
        }
        return userRoles.get(user);
    }
}
