package com.management.wuye.service.common;

import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import com.management.wuye.mapper.common.CommonMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Resource
    CommonMapper commonMapper;

    public Map<String, Object> getAllBuildings(int houseId, int page, int size, String buildingInfo) {

        page = (page - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        List<Buildings> buildings =  commonMapper.getAllBuildings(houseId, page, size, buildingInfo);
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

    public Map<String,Object> getHousePage(int bid,int page,int size, String houseInfo){
        page = (page-1)*10;
        Map<String,Object> map = new HashMap<>();
        List<House> house = commonMapper.getHousePage(bid,page, size, houseInfo);
        Integer count = commonMapper.getCount(bid);
        map.put("houses",house);
        map.put("count",count);
        return map;
    }

    public int getCount(int bid){
        return commonMapper.getCount(bid);
    }

    public boolean deleteBuilding(int bid){
        return commonMapper.deleteBuilding(bid);
    }

    public boolean updateBuilding(Buildings buildings){
        return  commonMapper.updateBuilding(buildings);
    }

    public boolean addBuilding(Buildings buildings){
        return commonMapper.addBuilding(buildings);
    }

    public boolean deleteHouse(String hid){
        return commonMapper.deleteHouse(hid);
    }

    public boolean updateHouse(House house){
        return commonMapper.updateHouse(house)  && commonMapper.upateUser(house);
    }

    public boolean addHouse(House house){
        return commonMapper.addHouse(house);
    }
}
