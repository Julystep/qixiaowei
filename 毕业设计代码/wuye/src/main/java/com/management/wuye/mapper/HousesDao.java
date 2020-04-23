package com.management.wuye.mapper;

import com.management.wuye.bean.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HousesDao {
    public List<House> getPage(int page, int size, String houseInfo);

    public Integer getCount();

    public Boolean addHouse(@Param("house") House house);

    public Boolean deleteHouse(@Param("hid")String hid);

    public int getHouseByName(String hname);

    public Boolean updateHouse(@Param("house") House house);
}
