package com.venn.service.impl;

import com.venn.constants.StaticConstants;
import com.venn.service.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenServiceImpl implements TokenService {

    private static List<String> tokens = new ArrayList<String>();

    @Override
    public String generateToken() {
        String timestamp = System.currentTimeMillis() + StaticConstants.EMPTY_STR;
        String token = UUID.randomUUID().toString().replace(StaticConstants.SPLIT_CHAR, "") + StaticConstants.SPLIT_CHAR + timestamp;
        synchronized (tokens) {
            tokens.add(token);
        }
        return token;
    }


    @Override
    public boolean checkToken(String token, long second) {
        if (null != token && !StaticConstants.EMPTY_STR.equals(token.trim())) {
            if (tokens.contains(token)) {
                long curTime = System.currentTimeMillis();
                long prevTime = Long.valueOf(token.substring(token.indexOf(StaticConstants.SPLIT_CHAR) + 1));
                long period = (curTime - prevTime) / 1000;
                if (period < second) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public boolean invalidateToken(String token) {
        if (null != token && !StaticConstants.EMPTY_STR.equals(token.trim()) && tokens.contains(token)) {
            synchronized (tokens) {
                if (tokens.contains(token)) {
                    tokens.remove(token);
                    return true;
                }
            }
        }
        return false;
    }

}
