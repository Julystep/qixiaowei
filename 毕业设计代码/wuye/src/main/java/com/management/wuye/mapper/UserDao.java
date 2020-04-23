package com.management.wuye.mapper;

import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    public User login(String loginName,String password);

    public List<User> getPage(int page, int size, String userInfo);

    public Integer getCount();

    public Boolean addUser(@Param("user")User user);

    public Boolean deleteUser(@Param("userId")String userId);

    public int getUserByName(String userName);

    public Boolean updateUser(@Param("user")User user);
}
