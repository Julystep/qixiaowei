package com.management.wuye.service.admin;

import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import com.management.wuye.bean.User;
import com.management.wuye.mapper.admin.AdminMapper;
import com.management.wuye.mapper.common.CommonMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    @Resource
    CommonMapper commonMapper;

    public Map<String, Object> getBuildingAndHouses(String userId, int page, int size, String houseInfo) {

        page = (page - 1) * 10;

        Buildings buildings = adminMapper.getBuilding(userId);

        List<House> houses = commonMapper.getHousePage(buildings.getBid(), page, size, houseInfo);

        int count = commonMapper.getCount(buildings.getBid());

        Map<String, Object> map = new HashMap<>();

        map.put("building", buildings);
        map.put("houses", houses);
        map.put("count", count);

        return map;

    }

    public boolean deleteHouse(String hid){
        return adminMapper.deleteHouse(hid);
    }

    public boolean updateHouse(House house){
        return adminMapper.updateHouse(house)  && adminMapper.upateUser(house);
    }

    public boolean addHouse(House house){
        return adminMapper.addHouse(house);
    }

    public List<User> getUsersWithoutHouses() {

        List<String> userIds = adminMapper.getUsersWithHouses();

        return adminMapper.getUsersWithoutHouses(userIds);

    }

    public boolean dismissWithUser(int id){
        return adminMapper.dismissWithUser(id);
    }
}
