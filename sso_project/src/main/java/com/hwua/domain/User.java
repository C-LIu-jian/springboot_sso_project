package com.hwua.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户id
    private String id;
   //姓名
    private String username;
    //密码
    private String password;
   //颜值
    private String salt;
   //电话
    private String phone;


}