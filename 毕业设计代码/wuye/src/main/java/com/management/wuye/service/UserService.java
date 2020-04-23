package com.management.wuye.service;

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
    public List<User> getPage(Integer page, Integer size, String userInfo){
        page = (page-1)*10;
        return userDao.getPage(page,size,userInfo);
    }

    public Integer getCount(){
        return userDao.getCount();
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
}
