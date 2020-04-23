package com.management.wuye.service.common;

import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import com.management.wuye.mapper.common.CommonMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Resource
    CommonMapper commonMapper;

    public Map<String, Object> getAllBuildings(int houseId, int page, int size) {

        page = (page - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        List<Buildings> buildings =  commonMapper.getAllBuildings(houseId, page, size);
        int buildingCounts = commonMapper.getAllBuildingsCount(houseId);
        map.put("buildings", buildings);
        map.put("buildingCounts", buildingCounts);
        return map;

    }

    public Map<String, Object> getAllHouses(int bid, int page, int size) {

        page = (page - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        List<House> houses =  commonMapper.getAllHouses(bid, page, size);
        int housesCount = commonMapper.getAllHousesCount(bid);
        map.put("houses", houses);
        map.put("housesCount", housesCount);
        return map;

    }
}
