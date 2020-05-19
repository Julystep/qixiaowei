package com.management.wuye.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.management.wuye.bean.Charge;
import com.management.wuye.mapper.user.RepairMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RepairService {
    @Resource
    RepairMapper repairMapper;

    public boolean submitRepair(String repairForm) {

        JSONObject jsonObject = JSON.parseObject(repairForm);

        String detail = jsonObject.getString("detail");

        String time = jsonObject.getString("time");

        Date date = new Date();

        String userid = jsonObject.getString("userid");

        int type = jsonObject.getInteger("type");

        boolean status = jsonObject.getBooleanValue("status");

        return repairMapper.submitRepair(detail, date, userid, type, status);

    }

    public List<Charge> getUserPaymentByUserId(String userId, Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String accDate = format.format(date);

        return repairMapper.getUserPaymentByUserId(userId, accDate);

    }
}
