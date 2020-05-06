package com.management.wuye.mapper.user;

import com.management.wuye.bean.Information;
import com.management.wuye.bean.Repair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoMapper {
    List<Information> getInfos(String info);
    List<Repair> getRepair(String userid, int page, int size, String repairInfo);
    int getCount();
}
