package com.management.wuye.service.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import com.management.wuye.bean.Information;
import com.management.wuye.bean.User;
import com.management.wuye.mapper.admin.AdminMapper;
import com.management.wuye.mapper.common.CommonMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

        List<House> houses = adminMapper.getHousePage(buildings.getBid(), page, size, houseInfo);

        int count = adminMapper.getHouseCount(buildings.getBid(), houseInfo);

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

        boolean flag1 = adminMapper.updateHouse(house);

        boolean flag2 = false;

        if(house.getUserName() != null || house.getUserName() != ""){
            flag2 = adminMapper.upateUser(house);
        }

        return flag1  && flag2;
    }

    public boolean addHouse(House house){
        return adminMapper.addHouse(house);
    }

    public List<User> getUsersWithoutHouses() {

        List<String> userIds = adminMapper.getUsersWithHouses();

        System.out.println(userIds);
        return adminMapper.getUsersWithoutHouses(userIds);

    }

    public boolean dismissWithUser(int id){
        return adminMapper.dismissWithUser(id);
    }

    public boolean submitInfomation(String ruleForm) {

        JSONObject jsonObject = JSON.parseObject(ruleForm);

        String head = jsonObject.getString("head");

        String content = jsonObject.getString("content");

        String infotime = jsonObject.getString("infotime");

        Date date = new Date();


        String userId = jsonObject.getString("userId");

        int type = jsonObject.getInteger("type");

        return adminMapper.submitInfomation(head, content, date, userId, type);

    }

    public Map<String, Object> getAllInformations(String info){
        List<Information> information = adminMapper.getAllInformations(info);

        Map<String, Object> map = new HashMap<>();

        map.put("information", information);
        return map;
    }

    public boolean deleteInfo(int id){
        return adminMapper.deleteInfo(id);
    }

    public Map<String, Object> getAllUsers(int id, int page, int size) {

        page = (page - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        List<User> users =  adminMapper.getAllUsers(id, page, size);
        int userCount = adminMapper.getAllUsersCount(id);
        map.put("houses", users);
        map.put("housesCount", userCount);
        return map;

    }
    public Map<String,Object> getUserList(String userid,int page,int size, String userInfo){
        page = (page-1)*10;

        Buildings buildings = adminMapper.getBuilding(userid);

        List<User> users = adminMapper.getUserPage(buildings.getBid(), page, size, userInfo);

        int usercount = adminMapper.getUserCount(buildings.getBid(), userInfo);

        Map<String, Object> map = new HashMap<>();

        map.put("buildings", buildings);
        map.put("users", users);
        map.put("usercount", usercount);

        return map;
    }

    public int getCount(int id){
        return commonMapper.getCount(id);
    }
}
