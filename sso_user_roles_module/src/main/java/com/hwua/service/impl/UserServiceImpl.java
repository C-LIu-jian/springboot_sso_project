package com.hwua.service.impl;

import com.hwua.domain.Permission;
import com.hwua.domain.Role;
import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.PasswordUtil;
import com.hwua.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Slf4j
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
         //获取用户
        User user = userMapper.selectByUsername(username);
        log.info(user+"----------user");
        //或得角色id
        String roleId = userMapper.selectUserRole(user.getId());
        log.info(roleId+"----------roleId");
        //或得角色
        Role role = userMapper.selectRole(roleId);
        log.info(role+"----------role");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        Set<Role> userroles = user.getRoles();
        Iterator<Role> it = userroles.iterator();
        while (it.hasNext()) {
            Role roleP = (Role) it.next();
            //获得菜单id
            String[] Role_Permissions = userMapper.selectRole_Permission(roleP.getId());
            Set<Permission> permissions = new HashSet<>();
            for (String id :Role_Permissions){
                Permission permission = userMapper.selectPermission(id);
                permissions.add(permission);
            }
            roleP.setPermissions(permissions);

        }

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
