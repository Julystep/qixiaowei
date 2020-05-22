package com.management.wuye.mapper.user;

import com.management.wuye.bean.Charge;
import com.management.wuye.bean.Repair;
import com.management.wuye.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface RepairMapper {
    int submitRepair(@Param("repair") Repair repair);

    List<Charge> getUserPaymentByUserId(String userId, String accDate);

    User getUserById(String userId);

    List<Charge> getUserPaymentByUserId1(String userId, String accDate);

    boolean setPath(int id, String json);
}
