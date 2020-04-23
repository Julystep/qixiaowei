package com.management.wuye.service.common;

import com.management.wuye.bean.Buildings;
import com.management.wuye.dao.common.CommonMapper;
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
}
