package com.management.wuye.dao.common;

import com.management.wuye.bean.Buildings;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

     List<Buildings> getAllBuildings(int houseId, int page, int size);

     Integer getAllBuildingsCount(int houseId);
}
