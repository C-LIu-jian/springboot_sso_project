package com.hwua.util;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
    public static String md5hash(String password, String username) {
        ByteSource salt = ByteSource.Util.bytes("7d69eec997034bfb8c5c");
        return new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 2019).toHex();
    }

    public static void main(String[] args) {
        //加密
        String hashAlgorithmName = Md5Hash.ALGORITHM_NAME;
        //密码
        String credentials = "123456";
        //加密次数
        int hashIterations = 2019;
        String salt="7d69eec997034bfb8c5c";
        ByteSource bytes = ByteSource.Util.bytes(salt);

        Object obj = new SimpleHash(hashAlgorithmName, credentials, bytes, hashIterations);//null 为盐
        System.out.println(obj);
    }

    }

   /* public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        //构建盐值加密类,指定加密方式为MD5
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
        //加盐次数
        hashedCredentialsMatcher.setHashIterations(2019);
        return hashedCredentialsMatcher;
    }*/