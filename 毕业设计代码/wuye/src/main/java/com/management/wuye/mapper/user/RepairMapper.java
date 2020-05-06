package com.management.wuye.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface RepairMapper {
    boolean submitRepair(String detail, Date time, String userid, int type, boolean status);
}
