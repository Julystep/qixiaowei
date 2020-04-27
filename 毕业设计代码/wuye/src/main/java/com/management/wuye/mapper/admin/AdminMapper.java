package com.management.wuye.mapper.admin;

import com.management.wuye.bean.Buildings;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Buildings getBuilding(String userId);

}
