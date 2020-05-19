package com.management.wuye.mapper.user;

import com.management.wuye.bean.Charge;
import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface RepairMapper {
    boolean submitRepair(String detail, Date time, String userid, int type, boolean status);

    List<Charge> getUserPaymentByUserId(String userId, String accDate);

    User getUserById(String userId);

    List<Charge> getUserPaymentByUserId1(String userId, String accDate);
}
