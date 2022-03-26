package com.venn.service.impl;

import com.venn.constants.StaticConstants;
import com.venn.po.User;
import com.venn.service.UserService;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<User>();

    @Override
    public boolean deleteUser(User user) {
        if (null != user && null != user.getUserName()
                && !StaticConstants.EMPTY_STR.equals(user.getUserName())
                && !userList.isEmpty() && userList.contains(user)) {
            synchronized (userList) {
                if (userList.contains(user)) {
                    userList.remove(user);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean createUser(String userName, String password) {
        if (null != userName && null != password
                && !StaticConstants.EMPTY_STR.equals(userName.trim())
                && !StaticConstants.EMPTY_STR.equals(password.trim())) {
            User user = new User(userName, password);
            if (!userList.contains(user)) {
                synchronized (userList) {
                    if (!userList.contains(user)) {
                        userList.add(user);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isValidate(String userName, String password) {
        if (null != userName && null != password
                && !StaticConstants.EMPTY_STR.equals(userName.trim())
                && !StaticConstants.EMPTY_STR.equals(password.trim())) {
            User user = new User(userName, password);
            synchronized (userList) {
                return userList.stream().filter(u -> u.getUserName().equals(userName) && u.getPassword().equals(user.getPassword())).count() > 0;

            }
        }
        return false;
    }

}
