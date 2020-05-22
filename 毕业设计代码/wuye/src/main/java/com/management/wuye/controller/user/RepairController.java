package com.management.wuye.controller.user;

import com.management.wuye.bean.Charge;
import com.management.wuye.service.user.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RepairController {
    @Autowired
    RepairService repairService;

    @RequestMapping(value = "/submitRepair", method = RequestMethod.POST)
    @ResponseBody
    public boolean submitRepair(@RequestParam("files") MultipartFile[] files, @RequestParam("refForm") String refForm) throws IOException {
        System.out.println(refForm);
        return repairService.submitRepair(refForm, files);
    }

    @RequestMapping(value = "/getuserpaymentbyuserid", method = RequestMethod.POST)
    public List<Charge> getUserPaymentByUserId(@RequestParam("userId") String userId,
                                               @RequestParam("date") Date date){

        return repairService.getUserPaymentByUserId(userId, date);

    }

}
