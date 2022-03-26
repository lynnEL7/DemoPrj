package com.venn.constants;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CommonUtil {

    public static String encryptPwd(String pwd) {
        byte[] pwdByte = pwd.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(pwdByte);
    }

    public static String decodePwd(String encodedPwd) {
        byte[] pwdByte = encodedPwd.getBytes(StandardCharsets.UTF_8);
        return new String(Base64.getDecoder().decode(encodedPwd));
    }
}
