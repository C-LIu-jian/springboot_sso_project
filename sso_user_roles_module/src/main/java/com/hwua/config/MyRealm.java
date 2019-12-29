package com.hwua.config;

import com.hwua.domain.Permission;
import com.hwua.domain.Role;
import com.hwua.domain.User;
import com.hwua.service.UserService;
import com.hwua.util.JWTUtil;
import com.hwua.util.MyJwtToken;
import com.hwua.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Set;

@Slf4j
public class MyRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

/*    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyJwtToken;
    }*/

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //把参数强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取用户名
        String username = usernamePasswordToken.getUsername();

        //根据用户名查询
        ResponseData responseData = null;
        try{
            User user = userService.getUserByUsername(username);
             //User user = (User) responseData.getT();
            //判断对象是否为空
            if (user!=null){
                //盐
                ByteSource bytes = ByteSource.Util.bytes(username);
                //构建对象
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),bytes,getName());
                return simpleAuthenticationInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = ((UsernamePasswordToken) principals.getPrimaryPrincipal()).getUsername();
        User userSuperInfoByUsername = userService.getUserInfoByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<Role> roleSet = userSuperInfoByUsername.getRoles();
        Iterator<Role> iterator = roleSet.iterator();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            simpleAuthorizationInfo.addRole(role.getName());
            Set<Permission> permissionSet = role.getPermissions();
            Iterator<Permission> iterator1 = permissionSet.iterator();
            while (iterator1.hasNext()) {
                Permission permission = iterator1.next();
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;

    }
}