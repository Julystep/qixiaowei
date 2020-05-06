package com.management.wuye.controller.user;

import com.management.wuye.service.user.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class InfoController {
    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/getinfos", method = RequestMethod.GET)
    public Map<String, Object> getInfos(@RequestParam("info") String info){

        return infoService.getInfos(info);
    }

    @RequestMapping(value = "/getrepair", method = RequestMethod.GET)
    public Map<String, Object> getRepair(@RequestParam("userid") String userid,
                                         @RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam("repairInfo") String repairInfo){

        return infoService.getRepair(userid, page, size, repairInfo);
    }

}
