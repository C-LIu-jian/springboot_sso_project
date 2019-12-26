package com.hwua.domain;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {
     //秘钥
    private  String token;

    public  JWTToken(String token){
        this.token=token;
    }
    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
