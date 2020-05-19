package com.management.wuye.service;

import com.management.wuye.bean.Charge;
import com.management.wuye.bean.User;
import com.management.wuye.mapper.user.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Value("${spring.url}")
    private String Url;

    @Resource
    RepairMapper repairMapper;

    public String sendEmail(String userId, Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String accDate = format.format(date);

        List<Charge> charge =  repairMapper.getUserPaymentByUserId1(userId, accDate);
        int summary = Integer.parseInt(charge.get(0).getWaterFee()) + Integer.parseInt(charge.get(0).getElectricityFee()) + Integer.parseInt(charge.get(0).getPropertyCosts());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("缴费通知");	//设置邮件标题
        message.setText("尊敬的用户,您好:\n"
                + "\n本月您的账单已出，为不影响您正常的生活, 请到缴费地点进行缴费，费用账单为: 水费: "
                + charge.get(0).getWaterFee() + "; 电费: " + charge.get(0).getElectricityFee() +
                "; 物业费: " + charge.get(0).getPropertyCosts() + "\n"
                + "合计: " + summary  + "\n"
               );	//设置邮件正文
        User user = repairMapper.getUserById(charge.get(0).getUserId());
        message.setTo(user.getEmail());	//设置收件人
        message.setFrom("957904176@qq.com");	//设置发件人
        javaMailSender.send(message);	//发送邮件

        return "true";

    }
}
