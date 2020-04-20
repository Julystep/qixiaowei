package com.management.wuye.controller;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.Repair;
import com.management.wuye.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RepairController {
    @Autowired
    RepairService repairService;

    @RequestMapping(value = "/getRepairPage", method = RequestMethod.GET)
    public Map<String,Object> getPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "5") Integer size,
                                      @RequestParam(value = "repairInfo",defaultValue = "") String repairInfo){
        Map<String,Object> map = new HashMap<>();
        Integer count = repairService.getCount();
        List<Repair> repair = repairService.getPage(page, size, repairInfo);
        map.put("repair",repair);
        map.put("count",count);
        return map;
    }
    @RequestMapping(value = "/addRepair",method = RequestMethod.POST)
    public Boolean addRepair(@RequestParam("repair") String info){
        Repair repair = JSON.parseObject(info,Repair.class);
//        System.out.println(house.toString());
        return repairService.addRepair(repair);
    }

    @RequestMapping(value = "/getRepairById",method = RequestMethod.POST)
    public int getRepairById(@RequestParam("repairid")int repairid){
        System.out.println(repairid);
        return repairService.getRepairById(repairid);
    }

    @RequestMapping(value = "/updateRepair",method = RequestMethod.POST)
    public Boolean updateRepair(@RequestParam("repair") String info){
        Repair repair = JSON.parseObject(info,Repair.class);
//        System.out.println(house.toString());
        return repairService.updateRepair(repair);
    }
}
