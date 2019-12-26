package com.hwua.service.impl;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;

import com.hwua.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
@Controller
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseData login(User srcUser) {
        ResponseData responseData = new ResponseData();
//        User user = userMapper.selectByUsername(srcUser);

        //获取登录的主体对象
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //把用户名和密码封装成UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(srcUser.getUsername(), srcUser.getPassword());
            try {
                //当前用户进行登陆 参数类型:AuthenticationToken
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                responseData.setCode(501);
                responseData.setMessage("用户名不存在");
                responseData.setT(srcUser);
                return responseData;
            } catch (IncorrectCredentialsException ice) {
                responseData.setCode(502);
                responseData.setMessage("密码不正确");
                responseData.setT(srcUser);
                return responseData;
            } catch (LockedAccountException lae) {
                responseData.setCode(503);
                responseData.setMessage("账号被锁定");
                responseData.setT(srcUser);
                return responseData;

            } catch (AuthenticationException ae) {
                responseData.setCode(504);
                responseData.setMessage("联系管理员");
                responseData.setT(srcUser);
                return responseData;
            }
            }
            return null;
    }


        }


