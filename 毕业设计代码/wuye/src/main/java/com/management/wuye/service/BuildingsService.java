package com.management.wuye.service;

import com.management.wuye.bean.Buildings;
import com.management.wuye.dao.BuildingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingsService {
    @Autowired
    BuildingsDao buildingsDao;

    public List<Buildings> getPage(int page, int size, String buildingInfo){
        page = (page-1)*5;
        return buildingsDao.getPage(page,size,buildingInfo);
    }

    public Integer getCount(){
        return buildingsDao.getCount();
    }

    public Boolean addBuildings(Buildings buildings){
        return buildingsDao.addBuildings(buildings);
    }

    public Boolean deleteBuildings(int bid){
        return buildingsDao.deleteBuildings(bid);
    }

    public int getBuildingsByName(String bname){
        return buildingsDao.getBuildingsByName(bname);
    }

    public Boolean updateBuildings(Buildings buildings){
        return buildingsDao.updateBuildings(buildings);
    }
}
