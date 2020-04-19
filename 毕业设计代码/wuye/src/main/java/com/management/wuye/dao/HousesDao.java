package com.management.wuye.dao;

import com.management.wuye.bean.Houses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HousesDao {
    public List<Houses> getPage(int page, int size, String houseInfo);

    public Integer getCount();

    public Boolean addHouse(@Param("house") Houses house);

    public Boolean deleteHouse(@Param("hid")String hid);

    public int getHouseByName(String hname);

    public Boolean updateHouse(@Param("house") Houses house);
}
