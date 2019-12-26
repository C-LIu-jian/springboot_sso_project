package com.hwua.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.hwua.domain.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
     //构建一个token
    public String encodeToken(User user) throws Exception {
        //获取JWTCreator中的Builder对象
        JWTCreator.Builder builder = JWT.create().withClaim("username", user.getUsername()).withClaim("loginDate", new Date(System.currentTimeMillis()+EXPIRE_TIME));
        //构建一个加密的凭证
        Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
        //构建token
        String token = builder.sign(algorithm);
        return token;
    }

    //解码token
    public  static String decodeTokenByKey(String token, String key) throws Exception {
        //构建解码器
        DecodedJWT decode = JWT.decode(token);
        //获取有效负载里面的数据
        String stringClaim = decode.getClaim(key).asString();
        return stringClaim;
    }

    //验证
    public  static boolean ckeckToken(User user,String token) throws  Exception{
        if (user==null||user.getPassword()==null||user.getPassword().trim().equals("")){
            return false;
        }
        //构建验证器
        JWTVerifier build = JWT.require(Algorithm.HMAC256(user.getPassword())).build();

        //执行验证方法
        String username = build.verify(token).getClaim("username").asString();
        if (username==null){
            return false;
        }
        return true;
    }
}
