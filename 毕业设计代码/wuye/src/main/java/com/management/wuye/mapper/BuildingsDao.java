package com.management.wuye.mapper;

import com.management.wuye.bean.Buildings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuildingsDao {
    public List<Buildings> getPage(int page, int size, String buildingInfo);

    public Integer getCount();

    public Boolean addBuildings(@Param("buildings") Buildings buildings);

    public Boolean deleteBuildings(@Param("bid")int bid);

    public int getBuildingsByName(String bname);

    public Boolean updateBuildings(@Param("buildings") Buildings buildings);
}
