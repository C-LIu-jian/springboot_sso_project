package com.hwua.util;

import com.hwua.domain.User;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {
    public static String md5hash(String password, String username) {
        ByteSource salt = ByteSource.Util.bytes("7d69eec997034bfb8c5c");
        return new SimpleHash(Md5Hash.ALGORITHM_NAME, password, salt, 2019).toHex();
    }

    /**
     * 盐值加密密码
     * @param user
     * @return
     */
    public static String md5password(User user) {
        //加密
        String hashAlgorithmName = Md5Hash.ALGORITHM_NAME;
        //密码
        String credentials =user.getPassword();
        //加密次数
        int hashIterations = 2019;
        //盐值
        String salt=user.getSalt();
        ByteSource bytes = ByteSource.Util.bytes(salt);

        Object obj = new SimpleHash(hashAlgorithmName, credentials, bytes, hashIterations);
        return  (String) obj;
    }

    }

   /* public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        //构建盐值加密类,指定加密方式为MD5
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
        //加盐次数
        hashedCredentialsMatcher.setHashIterations(2019);
        return hashedCredentialsMatcher;
    }*/