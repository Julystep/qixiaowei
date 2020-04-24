package com.management.wuye.controller.common;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.Buildings;
import com.management.wuye.bean.House;
import com.management.wuye.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommonController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/getallbuildings", method = RequestMethod.POST)
    public Map<String, Object> getAllBuildings(@RequestParam("houseId") int houseId,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size,
                                               @RequestParam("buildingInfo") String buildingInfo){

        return commonService.getAllBuildings(houseId, page, size,buildingInfo);

    }

    @RequestMapping(value = "/gethousesbybuildingid", method = RequestMethod.POST)
    public Map<String, Object> getAllHouses(@RequestParam("bid") int bid,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size
                                            ){

        return commonService.getAllHouses(bid, page, size);

    }

    @RequestMapping(value = "/getHousePage", method = RequestMethod.POST)
    public Map<String,Object> getHousePage(@RequestParam("bid") int bid,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size,
                                           @RequestParam("houseInfo") String houseInfo){
        return commonService.getHousePage(bid,page,size,houseInfo);
    }



    @RequestMapping(value = "/root/deleteBuilding",method = RequestMethod.DELETE)
    public boolean deleteBuilding(@RequestParam("bid")int bid){
        return commonService.deleteBuilding(bid);
    }

    @RequestMapping(value = "/root/updateBuilding",method = RequestMethod.POST)
    public boolean updateBuilding(@RequestParam("buildings") String info){
        Buildings buildings = JSON.parseObject(info,Buildings.class);
        return commonService.updateBuilding(buildings);
    }

    @RequestMapping(value = "/root/addBuilding",method = RequestMethod.POST)
    public boolean addBuilding(@RequestParam("buildings")String info){
        Buildings buildings = JSON.parseObject(info,Buildings.class);
        return commonService.addBuilding(buildings);
    }


    @RequestMapping(value = "/root/deleteHouse",method = RequestMethod.DELETE)
    public boolean deleteHouse(@RequestParam("hid")String hid){
        return commonService.deleteHouse(hid);
    }

    @RequestMapping(value = "/root/updateHouse",method = RequestMethod.POST)
    public boolean updateHouse(@RequestParam("house")String info){
        House houses = JSON.parseObject(info,House.class);
        return commonService.updateHouse(houses);
    }

    @RequestMapping(value = "/root/addHouse",method = RequestMethod.POST)
    public boolean addHouse(@RequestParam("house")String info){
        House houses = JSON.parseObject(info,House.class);
        return commonService.addHouse(houses);
    }

}
