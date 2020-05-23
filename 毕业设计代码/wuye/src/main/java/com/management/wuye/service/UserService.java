package com.management.wuye.service;

import com.alibaba.fastjson.JSONObject;
import com.management.wuye.bean.Information;
import com.management.wuye.bean.User;
import com.management.wuye.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User login(String loginName, String password){
        return userDao.login(loginName,password);
    }

    public Boolean addUser(User user){
        return userDao.addUser(user);
    }

    public Boolean deleteUser(String userId){
        return userDao.deleteUser(userId);
    }

    public int getUserByName(String userName){
        return userDao.getUserByName(userName);
    }

    public Boolean updateUser(User user){
        return userDao.updateUser(user);
    }

    public boolean changeUserfaceById(String userId, String path) {

        return userDao.changeUserfaceById(userId, path);

    }

    public String getUserfaceById(String userId) {

        return userDao.getUserfaceById(userId);

    }

    public Boolean publicChat(String chat, int id) {

        return userDao.publicChat(chat, id);

    }

    public Information publicChat1(int id) {

        return userDao.publicChat1(id);

    }
}
