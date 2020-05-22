package com.management.wuye.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.management.wuye.bean.Charge;
import com.management.wuye.bean.Repair;
import com.management.wuye.mapper.user.RepairMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RepairService {
    @Resource
    RepairMapper repairMapper;

    @Value("${file.rootPath}")
    private String ROOT_PATH;
    //图片存放根目录下的子目录
    @Value("${file.sonPath2}")
    private String SON_PATH;

    public boolean submitRepair(String repairForm, MultipartFile[] files) throws IOException {

        System.out.println(files.length);

        JSONObject jsonObject = JSON.parseObject(repairForm);

        String detail = jsonObject.getString("detail");


        Date date = new Date();

        String userid = jsonObject.getString("userid");

        int type = jsonObject.getInteger("type");

        boolean status = jsonObject.getBooleanValue("status");

        Repair repair = new Repair();

        repair.setDetail(detail);
        repair.setUserid(userid);
        repair.setType(type);
        repair.setTime(date);
        repair.setStatus(status);

        int id = repairMapper.submitRepair(repair);

        JSONArray jsonArray = new JSONArray();

        for(MultipartFile file : files){
            if(file.isEmpty()){
                return false;
            }
            String fileName = file.getOriginalFilename();
            String suffixName  = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;   //新文件名
            String userFileDir = repair.getId()+"";
            File dest = new File(ROOT_PATH + SON_PATH + userFileDir + '/'+  fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            String path = SON_PATH  + userFileDir + '/' + fileName;
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("imgUrl", path);
            jsonArray.add(jsonObject1);
        }

        String json = jsonArray.toString();
        return repairMapper.setPath(repair.getId(), json);

    }

    public List<Charge> getUserPaymentByUserId(String userId, Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String accDate = format.format(date);

        return repairMapper.getUserPaymentByUserId(userId, accDate);

    }
    private static void deleteDirectory(File file) {
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
                deleteDirectory(childFile);
            }
        }
    }
}
