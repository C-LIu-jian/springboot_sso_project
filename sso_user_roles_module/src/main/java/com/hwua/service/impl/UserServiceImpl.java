package com.hwua.service.impl;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.PasswordUtil;
import com.hwua.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if (user != null){
            return user;
        }
        return  null;
    }

    @Override
    public User getUserInfoByUsername(String username) {
        User user = userMapper.selectUserInfoByUsername(username);
        return user;
    }

    @Override
    public void changePasswordByUsername(String username) {

    }
    public User getUserByUsernameAndPwd(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }
      /*  if (PasswordUtil.checkPassword(password,user.getPassword())){
            return user;*/
     /*   }

    }*/
        return null;
    }
}
