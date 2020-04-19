package com.management.wuye.controller;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.Houses;
import com.management.wuye.service.HousesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HousesController {
    @Autowired
    HousesService housesService;

    @RequestMapping(value = "/getHousePage", method = RequestMethod.GET)
    public Map<String,Object> getPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size,
                                      @RequestParam(value = "houseInfo",defaultValue = "") String houseInfo){
        Map<String,Object> map = new HashMap<>();
        Integer count = housesService.getCount();
        List<Houses> house = housesService.getPage(page, size, houseInfo);
        map.put("houses",house);
        map.put("count",count);
        return map;
    }

    @RequestMapping(value = "/addHouse",method = RequestMethod.POST)
    public Boolean addHouse(@RequestParam("house") String info){
        Houses house = JSON.parseObject(info,Houses.class);
//        System.out.println(house.toString());
        return housesService.addHouse(house);
    }

    @RequestMapping(value = "/deleteHouse",method = RequestMethod.DELETE)
    public Boolean deleteHouse(@RequestParam("hid")String hid){
        System.out.println(hid);
        return housesService.deleteHouse(hid);
    }

    @RequestMapping(value = "/getHouseByName",method = RequestMethod.POST)
    public int getHouseByName(@RequestParam("hname")String hname){
        System.out.println(hname);
        return housesService.getHouseByName(hname);
    }

    @RequestMapping(value = "/updateHouse",method = RequestMethod.POST)
    public Boolean updateHouse(@RequestParam("house") String info){
        Houses house = JSON.parseObject(info,Houses.class);
        System.out.println(house.toString());
        return housesService.updateHouse(house);
    }

}
