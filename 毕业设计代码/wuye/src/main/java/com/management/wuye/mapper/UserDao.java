package com.management.wuye.mapper;

import com.alibaba.fastjson.JSONObject;
import com.management.wuye.bean.Information;
import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    public User login(String loginName,String password);

    public Boolean addUser(@Param("user")User user);

    public Boolean deleteUser(@Param("userId")String userId);

    public int getUserByName(String userName);

    public Boolean updateUser(@Param("user")User user);

    boolean changeUserfaceById(String userId, String path);

    String getUserfaceById(String userId);

    Boolean publicChat(String chat, int id);

    Information publicChat1(int id);
}
