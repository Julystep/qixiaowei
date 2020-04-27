package com.management.wuye.controller.admin;

import com.management.wuye.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/getBuildingAndHouses", method = RequestMethod.GET)
    public Map<String, Object> getBuildingAndHouses(@RequestParam("userId") String userId,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size){

        return adminService.getBuildingAndHouses(userId, page, size);
    }

}
