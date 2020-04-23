package com.management.wuye.service;

import com.management.wuye.bean.House;
import com.management.wuye.mapper.HousesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousesService {
    @Autowired
    HousesDao housesDao;

    public List<House> getPage(int page, int size, String houseInfo){
        page = (page-1)*5;
        return housesDao.getPage(page,size,houseInfo);
    }

    public Integer getCount(){
        return housesDao.getCount();
    }

    public Boolean addHouse(House house){
        return housesDao.addHouse(house);
    }

    public Boolean deleteHouse(String hid){
        return housesDao.deleteHouse(hid);
    }

    public int getHouseByName(String hname){
        return housesDao.getHouseByName(hname);
    }

    public Boolean updateHouse(House house){
        return housesDao.updateHouse(house);
    }
}
