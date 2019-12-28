package com.hwua.action;

import com.hwua.domain.User;
import com.hwua.service.UserService;
import com.hwua.util.JWTUtil;
import com.hwua.util.MyJwtToken;
import com.hwua.util.PasswordUtil;
import com.hwua.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        //构建UsernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            //执行登录
            subject.login(usernamePasswordToken);
            //判断是否登录成功
            if (subject.isAuthenticated()){
                //设置成功的返回值
                String token = JWTUtil.createToken(user.getUsername(),user.getPassword());
                responsedata.setCode(0);
                responsedata.setMessage(token);
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
