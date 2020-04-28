package com.management.wuye.mapper.admin;

import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import com.management.wuye.bean.Information;
import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AdminMapper {

    Buildings getBuilding(String userId);

    List<User> getUsersWithoutHouses(@Param("userIds") List<String> userIds);

    List<String> getUsersWithHouses();

    boolean dismissWithUser(int id);

    boolean deleteHouse(@Param("hid")String hid);

    boolean updateHouse(@Param("house")House house);

    boolean addHouse(@Param("house")House house);

    boolean upateUser(@Param("house")House house);

    List<House> getHousePage(int bid, int page, int size, String houseInfo);

    int getHouseCount(int bid, String houseInfo);

    boolean submitInfomation(String head, String content, Date infotime, String userId, int type);

    List<Information> getAllInformations(String info);

    boolean deleteInfo(@Param("id") int id);
}
