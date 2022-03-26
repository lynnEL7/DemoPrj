package com.venn.service;

public interface TokenService {

    public String generateToken();

    /**
     * @param token
     * @param second - the token valid period,counted by second
     * @return true - token is valid; false - token is not valid or not exists
     */
    public boolean checkToken(String token, long second);

    /**
     * @param token
     * @return true: token is invalidated, false: no
     */
    public boolean invalidateToken(String token);
}
