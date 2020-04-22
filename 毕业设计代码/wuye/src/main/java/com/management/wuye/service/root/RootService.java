package com.management.wuye.service.root;

import com.management.wuye.bean.User;
import com.management.wuye.dao.root.RootMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RootService {

    @Resource
    RootMapper rootMapper;

    public Map<String, Object> getAllAdmins(int page, int size, String userInfo) {

        page = (page - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        List<User> users = rootMapper.getAllAdmins(page, size, userInfo);
        int count = rootMapper.getAllAdminCounts(userInfo);
        map.put("admins", users);
        map.put("count", count);
        return map;

    }
}
