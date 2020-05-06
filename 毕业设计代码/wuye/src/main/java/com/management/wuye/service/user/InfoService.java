package com.management.wuye.service.user;

import com.management.wuye.bean.Information;
import com.management.wuye.bean.Repair;
import com.management.wuye.mapper.user.InfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoService {
    @Resource
    InfoMapper infoMapper;

    public Map<String, Object> getInfos(String info){

        List<Information> information = infoMapper.getInfos(info);

        Map<String, Object> map = new HashMap<>();

        map.put("information", information);
        return map;
    }

    public Map<String, Object> getRepair(String userid, int page, int size, String repairInfo){

        List<Repair> repair = infoMapper.getRepair(userid, page, size,repairInfo);

        int count = infoMapper.getCount();

        Map<String, Object> map = new HashMap<>();

        map.put("repair", repair);
        map.put("count",count);
        return map;
    }
}
