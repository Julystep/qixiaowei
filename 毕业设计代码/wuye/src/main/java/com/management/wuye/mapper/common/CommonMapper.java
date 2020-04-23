package com.management.wuye.mapper.common;

import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

     List<Buildings> getAllBuildings(int houseId, int page, int size);

     Integer getAllBuildingsCount(int houseId);

     List<House> getAllHouses(int bid, int page, int size);

     int getAllHousesCount(int bid);
}
