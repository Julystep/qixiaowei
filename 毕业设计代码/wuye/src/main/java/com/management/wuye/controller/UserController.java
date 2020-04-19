package com.management.wuye.controller;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.User;
import com.management.wuye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/getUserPage", method = RequestMethod.GET)
    public Map<String,Object> getPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size,
                                      @RequestParam(value = "userInfo",defaultValue = "") String userInfo){
        Map<String,Object> map = new HashMap<>();
        Integer count = userService.getCount();
        List<User> user = userService.getPage(page, size, userInfo);
        map.put("user",user);
        map.put("count",count);
        return map;
    }

    @RequestMapping(value = "/user/addUser",method = RequestMethod.POST)
    public Boolean addUser(@RequestParam("user") String info){
        User user = JSON.parseObject(info,User.class);
        System.out.println(user.toString());
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/deleteUser",method = RequestMethod.DELETE)
    public Boolean deleteUser(@RequestParam("userId")String userId){
        System.out.println(userId);
        return userService.deleteUser(userId);
    }

    @RequestMapping(value = "/user/updateUser",method = RequestMethod.POST)
    public Boolean updateUser(@RequestParam("user")String info){
        User user = JSON.parseObject(info,User.class);
        System.out.println(user.toString());
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/getUserByName",method = RequestMethod.POST)
    public int getUserByName(@RequestParam("userName")String userName){
        System.out.println(userName);
        return userService.getUserByName(userName);
    }
}
