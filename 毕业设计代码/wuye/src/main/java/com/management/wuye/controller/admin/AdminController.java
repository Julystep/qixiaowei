package com.management.wuye.controller.admin;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.House;
import com.management.wuye.bean.User;
import com.management.wuye.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/getBuildingAndHouses", method = RequestMethod.GET)
    public Map<String, Object> getBuildingAndHouses(@RequestParam("userId") String userId,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("houseInfo") String houseInfo){

        return adminService.getBuildingAndHouses(userId, page, size, houseInfo);
    }

    @RequestMapping(value = "/deleteHouse",method = RequestMethod.DELETE)
    public boolean deleteHouse(@RequestParam("hid")String hid){
        return adminService.deleteHouse(hid);
    }

    @RequestMapping(value = "/updateHouse",method = RequestMethod.POST)
    public boolean updateHouse(@RequestParam("house")String info){
        House houses = JSON.parseObject(info,House.class);
        return adminService.updateHouse(houses);
    }

    @RequestMapping(value = "/addHouse",method = RequestMethod.POST)
    public boolean addHouse(@RequestParam("house")String info){
        House houses = JSON.parseObject(info,House.class);
        return adminService.addHouse(houses);
    }

    @RequestMapping(value = "/getuserswithouthouses", method = RequestMethod.GET)
    public List<User> getAdminsWithoutHouses(){
        return adminService.getUsersWithoutHouses();
    }

    @RequestMapping(value = "/dismissWithUser", method = RequestMethod.POST)
    public boolean dismissWithUser(@RequestParam("id") int id){
        return adminService.dismissWithUser(id);

    }

    @RequestMapping(value = "/submitInfomation", method = RequestMethod.POST)
    public boolean submitInfomation(@RequestParam("ruleForm") String ruleForm){
        return adminService.submitInfomation(ruleForm);
    }

    @RequestMapping(value = "/getallinformations", method = RequestMethod.GET)
    public Map<String, Object> getAllInformations(@RequestParam("info") String info){

        return adminService.getAllInformations(info);
    }

    @RequestMapping(value = "/deleteInfo", method = RequestMethod.DELETE)
    public boolean deleteInfo(@RequestParam("id") int id){
        return adminService.deleteInfo(id);
    }

    @RequestMapping(value = "/getalluser", method = RequestMethod.GET)
    public Map<String, Object> getAllUsers(@RequestParam("id") int id,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size
    ){

        return adminService.getAllUsers(id, page, size);

    }

    @RequestMapping(value = "/getUserPage", method = RequestMethod.GET)
    public Map<String,Object> getUserList(@RequestParam(value = "userid", required = true) String userid,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("size") Integer size,
                                          @RequestParam("userInfo") String userInfo){
        return adminService.getUserList(userid,page,size,userInfo);
    }
}
