package com.management.wuye.controller.user;

import com.management.wuye.service.user.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RepairController {
    @Autowired
    RepairService repairService;

    @RequestMapping(value = "/submitRepair", method = RequestMethod.POST)
    public boolean submitRepair(@RequestParam("repairForm") String repairForm){
        return repairService.submitRepair(repairForm);
    }
}
