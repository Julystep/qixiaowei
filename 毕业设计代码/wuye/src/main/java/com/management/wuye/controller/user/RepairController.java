package com.management.wuye.controller.user;

import com.management.wuye.bean.Charge;
import com.management.wuye.service.user.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RepairController {
    @Autowired
    RepairService repairService;

    @RequestMapping(value = "/submitRepair", method = RequestMethod.POST)
    public boolean submitRepair(@RequestParam("repairForm") String repairForm){
        return repairService.submitRepair(repairForm);
    }

    @RequestMapping(value = "/getuserpaymentbyuserid", method = RequestMethod.POST)
    public List<Charge> getUserPaymentByUserId(@RequestParam("userId") String userId,
                                               @RequestParam("date") Date date){

        return repairService.getUserPaymentByUserId(userId, date);

    }

}
