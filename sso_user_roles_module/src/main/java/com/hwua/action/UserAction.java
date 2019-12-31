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

        ResponseData result= new ResponseData();
//            userService.login(vo);
            return result;
        }


    @PutMapping("/user/pwd")
    public void pwd(String oldPwd,String newPwd,String rePass) throws Exception{
        System.out.println(oldPwd+"   "+newPwd+"   "+rePass);

    }

}
