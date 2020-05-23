package com.management.wuye.controller.admin;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.House;
import com.management.wuye.bean.Information;
import com.management.wuye.bean.Repair;
import com.management.wuye.bean.User;
import com.management.wuye.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
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
    public boolean submitInfomation(@RequestParam("files") MultipartFile []files, @RequestParam("ruleForm") String ruleForm) throws IOException {
        return adminService.submitInfomation(files, ruleForm);
    }

    @RequestMapping(value = "/getallinformations", method = RequestMethod.GET)
    public Map<String, Object> getAllInformations(@RequestParam("info") String info, @RequestParam("userid") String userid){

        return adminService.getAllInformations(info, userid);
    }

    @RequestMapping(value = "/deleteInfo", method = RequestMethod.DELETE)
    public boolean deleteInfo(@RequestParam("id") int id){
        return adminService.deleteInfo(id);
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public boolean updateInfo(@RequestParam("item")String info){
        Information information = JSON.parseObject(info,Information.class);
        System.out.println(information.toString());
        return adminService.updateInfo(information);
    }

    @RequestMapping(value = "/getUserPage", method = RequestMethod.POST)
    public Map<String,Object> getUserList(@RequestParam("userid") String userid,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("size") Integer size,
                                          @RequestParam("userInfo") String userInfo){
        return adminService.getUserList(userid,page,size,userInfo);
    }

    @RequestMapping(value = "/getUserPageBytime", method = RequestMethod.POST)
    public Map<String,Object> getUserListByDate(@RequestParam("userid") String userid,
                                                @RequestParam("page") Integer page,
                                                @RequestParam("size") Integer size,
                                                @RequestParam("userInfo") String userInfo,
                                                @RequestParam("date") Date date){
        return adminService.getUserListByDate(userid,page,size,userInfo, date);
    }

    @RequestMapping(value = "/getallrepairs", method = RequestMethod.GET)
    public Map<String, Object> getAllRepairs(@RequestParam("userId") String userId,
                                             @RequestParam("page") int page,
                                             @RequestParam("size") int size,
                                             @RequestParam("repairInfo") String repairInfo){

        return adminService.getAllRepairs(userId, page, size, repairInfo);
    }

    @RequestMapping(value = "/deleteRepair", method = RequestMethod.DELETE)
    public boolean deleteRepair(@RequestParam("id") int id){
        return adminService.deleteRepair(id);
    }

    @RequestMapping(value = "/updateRepair",method = RequestMethod.POST)
    public boolean updateRepair(@RequestParam("repair")String info){
        Repair repair = JSON.parseObject(info,Repair.class);
        return adminService.updateRepair(repair);
    }

    @RequestMapping(value = "/insertRecording", method = RequestMethod.POST)
    public boolean insertRecording(@RequestParam("addFormData")String addFormData){

        return adminService.insertRecording(addFormData);

    }

    @RequestMapping(value = "/importRecordings", method = RequestMethod.POST)
    public boolean importRecordings(MultipartFile file){

        return adminService.importRecordings(file);

    }

    @RequestMapping(value = "/exportRecordings", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportRecordings(@RequestParam("userid") String userid,
                                                   @RequestParam("date") String date) throws Exception {


        return adminService.exportRecordings(userid, date);

    }

    @RequestMapping(value = "/changeState", method = RequestMethod.POST)
    public boolean changeState(@RequestParam("userId") String userId,
                               @RequestParam("date") String date){

        return adminService.changeState(userId, date);

    }


}
