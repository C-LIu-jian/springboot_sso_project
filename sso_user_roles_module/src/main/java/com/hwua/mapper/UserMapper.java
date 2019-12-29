package com.hwua.mapper;


import com.hwua.domain.Permission;
import com.hwua.domain.Role;
import com.hwua.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    /**
     * 或得角色id
     * @param id
     * @return
     */
    @Select("select role_id from user_role where user_id=#{id}")
     String selectUserRole(String id);

    /**
     * 或得角色
     * @param id
     * @return
     */
     @Select("select * from role where id=#{id}")
      Role selectRole(String id);
    @Select("select permission_id from role_permission where role_id=#{role_id}")
     String[] selectRole_Permission(String role_id);
    /**
     * 或得菜单
     * @param id
     * @return
     */
    @Select("select * from permission where id=#{id}")
     Permission selectPermission(String id);
}