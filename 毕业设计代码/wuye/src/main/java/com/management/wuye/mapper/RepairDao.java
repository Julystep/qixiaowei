package com.management.wuye.mapper;

import com.management.wuye.bean.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RepairDao {
    public List<Repair> getPage(int page, int size, String repairInfo);

    public Integer getCount();

    public Boolean addRepair(@Param("repair") Repair repair);

    public int getRepairById(int repairid);

    public Boolean updateRepair(@Param("repair") Repair repair);
}
