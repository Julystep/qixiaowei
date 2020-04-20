package com.management.wuye.service;

import com.management.wuye.bean.Repair;
import com.management.wuye.dao.RepairDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    @Autowired
    RepairDao repairDao;

    public List<Repair> getPage(int page, int size, String repairInfo){
        page = (page-1)*5;
        return repairDao.getPage(page,size,repairInfo);
    }

    public Integer getCount(){
        return repairDao.getCount();
    }

    public Boolean addRepair(Repair repair){
        return repairDao.addRepair(repair);
    }

    public int getRepairById(int repairid){
        return repairDao.getRepairById(repairid);
    }

    public Boolean updateRepair(Repair repair){
        return repairDao.updateRepair(repair);
    }
}
