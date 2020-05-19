package com.management.wuye.controller;

import com.management.wuye.bean.User;
import com.management.wuye.service.MailService;
import com.management.wuye.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MailController {

    @Autowired
    MailService mailService;

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/mail/sendMail", method = RequestMethod.POST)
    public String getMail(@RequestParam("userId") String userId,
                                @RequestParam("date") Date date){

        try{
            String verCode = mailService.sendEmail(userId, date);
            return verCode;
        }catch (Exception e){
            return "发送失败";
        }

    }

    @Scheduled(cron = "0 0 12 * * *")
    @RequestMapping(value = "/mail/sendMails", method = RequestMethod.POST)
    public String getMails(){

        try{
            List<User> users = adminService.getUsers();
            for(User user : users){
                if(user.getCharge() != null){
                    if(user.getCharge().getState() == 0){
                        String verCode = mailService.sendEmail(user.getUserId(), user.getCharge().getDate());
                    }
                }
            }
            return "verCode";
        }catch (Exception e){
            return "发送失败";
        }

    }

}
