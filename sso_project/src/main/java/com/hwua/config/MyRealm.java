package com.hwua.config;

import com.hwua.domain.JWTToken;
import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  boolean supports(AuthenticationToken token){
        return  token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //把参数强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = usernamePasswordToken.getUsername();
        //根据用户名查询
        User user = null;
        try{
           /* User paramUser = new User();
            paramUser.setName(username);*/
            System.out.println(username+"-----用户名");
           user = userMapper.selectByUsername(username);

            System.out.println(user+"-----查询的对象");
            //判断对象是否为空
            if (user!=null){
                //使用用户名作为盐
                ByteSource bytes = ByteSource.Util.bytes(user.getSalt());

                //构建对象
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),bytes,getName());
                //获取当前的会话对象
                Subject subject = SecurityUtils.getSubject();
                return simpleAuthenticationInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
