package com.management.wuye.mapper.common;

import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommonMapper {

     List<Buildings> getAllBuildings(int houseId, int page, int size, String buildingInfo);

     Integer getAllBuildingsCount(int houseId);

     boolean deleteBuilding(@Param("bid") int bid);

     boolean updateBuilding(@Param("buildings")Buildings buildings);

     boolean addBuilding(@Param("buildings")Buildings buildings);

     List<House> getAllHouses(int bid, int page, int size);

     int getAllHousesCount(int bid);

     public List<House> getHousePage(int bid,int page, int size, String houseInfo);

     public Integer getCount(int bid);

     boolean deleteHouse(@Param("hid")String hid);

     boolean updateHouse(@Param("house")House house);

     boolean addHouse(@Param("house")House house);

}
