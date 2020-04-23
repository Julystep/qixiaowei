package com.management.wuye.controller.common;

import com.management.wuye.bean.Buildings;
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
                                               @RequestParam("size") int size){

        return commonService.getAllBuildings(houseId, page, size);

    }

}
