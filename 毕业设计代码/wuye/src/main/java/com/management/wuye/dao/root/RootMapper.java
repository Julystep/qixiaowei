package com.management.wuye.dao.root;

import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RootMapper {

    List<User> getAllAdmins(int page, int size, String userInfo);

    int getAllAdminCounts(String userInfo);
}
