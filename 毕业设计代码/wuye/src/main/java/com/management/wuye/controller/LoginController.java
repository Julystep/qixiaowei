package com.management.wuye.controller;

import com.management.wuye.bean.User;
import com.management.wuye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam("loginName")String loginName, @RequestParam("password")String password) {
        User user = userService.login(loginName, password);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


}
