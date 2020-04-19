package com.management.wuye.controller;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.Buildings;
import com.management.wuye.service.BuildingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BuildingsController {
    @Autowired
    BuildingsService buildingsService;


    @RequestMapping(value = "/getBuildingsPage", method = RequestMethod.GET)
    public Map<String,Object> getPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size,
                                      @RequestParam(value = "buildingInfo",defaultValue = "") String buildingInfo){
        Map<String,Object> map = new HashMap<>();
        Integer count = buildingsService.getCount();
        List<Buildings> build = buildingsService.getPage(page, size, buildingInfo);
        map.put("build",build);
        map.put("count",count);
        return map;
    }

    @RequestMapping(value = "/addBuildings",method = RequestMethod.POST)
    public Boolean addBuildings(@RequestParam("buildings") String info){
        Buildings build = JSON.parseObject(info,Buildings.class);
        System.out.println(build.toString());
        return buildingsService.addBuildings(build);
    }

    @RequestMapping(value = "/deleteBuildings",method = RequestMethod.DELETE)
    public Boolean deleteBuildings(@RequestParam("bid")int bid){
        System.out.println(bid);
        return buildingsService.deleteBuildings(bid);
    }

    @RequestMapping(value = "/updateBuildings",method = RequestMethod.POST)
    public Boolean updateBuildings(@RequestParam("buildings")String info){
        Buildings build = JSON.parseObject(info,Buildings.class);
        System.out.println(build.toString());
        return buildingsService.addBuildings(build);
    }

    @RequestMapping(value = "/getBuildingsByName",method = RequestMethod.POST)
    public int getBuildingsByName(@RequestParam("bname")String bname){
        System.out.println(bname);
        return buildingsService.getBuildingsByName(bname);
    }
}
