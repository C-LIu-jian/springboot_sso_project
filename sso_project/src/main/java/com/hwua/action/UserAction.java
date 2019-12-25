package com.hwua.action;

import com.hwua.domain.User;
import com.hwua.service.UserService;
import com.hwua.util.ResponseData;
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
@Controller
public class UserAction {

    @Autowired
    private UserService userService;


    @RequestMapping("/user/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        ResponseData login = userService.login(user);
        ModelAndView mv = new ModelAndView();

        //判断是否认证
        if (login == null) {
            System.out.println("跳转页面");
//           mv.setViewName("/main.html");
            return "hello";

        } else {
            System.out.println("错误信息");
//           mv.setViewName("/login.html");
          return "login";


        }
//      return mv;
    }

}
