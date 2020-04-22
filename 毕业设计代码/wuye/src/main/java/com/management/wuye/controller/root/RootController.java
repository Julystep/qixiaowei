package com.management.wuye.controller.root;

import com.management.wuye.bean.User;
import com.management.wuye.service.root.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/root")
public class RootController {

    @Autowired
    RootService rootService;

    @RequestMapping(value = "/getalladmins", method = RequestMethod.GET)
    public Map<String, Object> getAllAdmins(@RequestParam("page") int page,
                                            @RequestParam("size") int size,
                                            @RequestParam("userInfo") String userInfo){

        return rootService.getAllAdmins(page, size, userInfo);

    }

}
