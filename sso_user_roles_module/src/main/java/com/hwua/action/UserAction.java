package com.hwua.action;

import com.hwua.domain.Permission;
import com.hwua.domain.Role;
import com.hwua.domain.User;
import com.hwua.service.UserService;
import com.hwua.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserAction {
    @Autowired
    private UserService userService;


    @PostMapping("/user/login")
    public ResponseData login(@RequestBody User user) throws Exception{
     

        //获取当前对象
        Subject subject = SecurityUtils.getSubject();
        //创建返回对象
        ResponseData responsedata = new ResponseData();

        //token

        //构建UsernamePasswordToken对象
       UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try {
            //执行登录
            subject.login(usernamePasswordToken);
            //判断是否登录成功
            if (subject.isAuthenticated()){
                User userInfoByUser = userService.getUserInfoByUsername(user.getUsername());
                //设置成功的返回值
                Map<String,Object> claims=new HashMap<>();
                //角色
                Set<Role> roles = userInfoByUser.getRoles();
                //权限
                Set<Permission> permissions = new HashSet<>();
                Iterator<Role> it = roles.iterator();
                while (it.hasNext()) {
                    Role roleP = (Role) it.next();
                    permissions = roleP.getPermissions();
                }

                claims.put(Constant.JWT_PERMISSIONS_KEY,permissions);
                claims.put(Constant.JWT_ROLES_KEY,roles);
                String access_token=JWTUtil.createToken(user.getUsername(),user.getPassword(),claims);

                responsedata.setCode(0);
                responsedata.setMessage(access_token);
            }
        }catch ( UnknownAccountException uae ) {
            //未知账户异常   用户名不存在
            responsedata.setMessage("用户名不存在!");
        } catch ( IncorrectCredentialsException ice ) {
            //密码无法匹配异常
            responsedata.setMessage("密码无法匹配异常!");
        } catch ( LockedAccountException lae ) {
            //账户锁定异常
            responsedata.setMessage("账户锁定异常!");
        } catch ( ExcessiveAttemptsException eae ) {
            //错误次数过多异常
            responsedata.setMessage("错误次数过多异常!");
        } catch ( AuthenticationException ae ) {
            //认证错误异常
            responsedata.setMessage("认证错误异常!");
        }
        return responsedata;
    }

    @PutMapping("/user/pwd")
    public void pwd(String oldPwd,String newPwd,String rePass) throws Exception{
        System.out.println(oldPwd+"   "+newPwd+"   "+rePass);

    }

}
