package com.management.wuye.mapper.root;

import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RootMapper {

    List<User> getAllAdmins(int page, int size, String userInfo);

    int getAllAdminCounts(String userInfo);

    Boolean addAdmin(@Param("admin") User user);

    Boolean deleteAdmin(@Param("userId") String userId);

    Boolean updateAdmin(@Param("admin") User user);

    List<User> getAdminsWithoutHouses(@Param("userIds") List<String> userIds);

    boolean dismissWithAdmin(int bid);

    List<String> getAdminsWithHouses();
}
