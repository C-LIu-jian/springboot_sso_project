package com.hwua.action;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.JwtUtil;
import com.hwua.util.MD5Util;
import com.hwua.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
@RestController
public class UserAction {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/login")
    public ResponseData login(@RequestBody User user) throws Exception {
//        //获取数据库user对象
        User byUsername = userMapper.selectByUsername(user.getUsername());
//        //对user进行密码盐值加密
//        user.setPassword( MD5Util.md5password(user));
        //判断密码是否一样
//        if (user.getPassword().equals(byUsername.getPassword())){
//
//        }
        ResponseData login = userService.login(user);
        ResponseData responseData= new ResponseData(200, "登陆成功", new JwtUtil().encodeToken(byUsername));

        return responseData;
    }

}
