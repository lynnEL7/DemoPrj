package com.venn.po;

import com.venn.constants.CommonUtil;
import com.venn.constants.StaticConstants;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class User {

    private static String salt = "venn@2022";

    String userName;

    String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }


    public User(String userName, String password) {

        this.userName = userName;
        this.password = CommonUtil.encryptPwd(password + salt);

    }

    public static String getDecodedPassword(String decodedPwd) {
        if (null != decodedPwd && !StaticConstants.EMPTY_STR.equals(decodedPwd)
                && decodedPwd.contains(salt)) {
            return decodedPwd.substring(0, decodedPwd.indexOf(salt));
        }
        return null;
    }

}
