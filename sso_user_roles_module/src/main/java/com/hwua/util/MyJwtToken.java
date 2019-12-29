package com.hwua.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 提供原来的UsernamePasswordToken的增强版
 * 此时
 * username   是使用密码作为凭证加密之后的用户名
 * password   原来的密码还是原来的密码
 */
public class MyJwtToken implements AuthenticationToken {


    private String token;
    private String password;

    public MyJwtToken(String token) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
