package com.venn.service;

import com.venn.po.User;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    /**
     * @param user
     * @return true: if user exists and is deleted, false:neither
     */
    public boolean deleteUser(User user);

    /**
     * @param userName
     * @param password
     * @return true: user is created, false: user is not valid or user exists
     */
    public boolean createUser(String userName, String password);

    /**
     * @param userName
     * @param password
     * @return true:user exists, false:user not exits or user information is not right
     */
    public boolean isValidate(String userName, String password);


}
