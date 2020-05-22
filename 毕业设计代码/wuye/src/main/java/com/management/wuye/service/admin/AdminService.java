package com.management.wuye.service.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.management.wuye.bean.*;
import com.management.wuye.mapper.admin.AdminMapper;
import com.management.wuye.mapper.common.CommonMapper;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    @Resource
    CommonMapper commonMapper;

    public Map<String, Object> getBuildingAndHouses(String userId, int page, int size, String houseInfo) {

        page = (page - 1) * 10;

        Buildings buildings = adminMapper.getBuilding(userId);

        List<House> houses = adminMapper.getHousePage(buildings.getBid(), page, size, houseInfo);

        int count = adminMapper.getHouseCount(buildings.getBid(), houseInfo);

        Map<String, Object> map = new HashMap<>();

        map.put("building", buildings);
        map.put("houses", houses);
        map.put("count", count);

        return map;

    }

    public boolean deleteHouse(String hid) {
        return adminMapper.deleteHouse(hid);
    }

    public boolean updateHouse(House house) {

        boolean flag1 = adminMapper.updateHouse(house);

        boolean flag2 = false;

        if (house.getUserName() != null || house.getUserName() != "") {
            flag2 = adminMapper.upateUser(house);
        }

        return flag1 && flag2;
    }

    public boolean addHouse(House house) {
        return adminMapper.addHouse(house);
    }

    public List<User> getUsersWithoutHouses() {

        List<String> userIds = adminMapper.getUsersWithHouses();

        System.out.println(userIds);
        return adminMapper.getUsersWithoutHouses(userIds);

    }

    public boolean dismissWithUser(int id) {
        return adminMapper.dismissWithUser(id);
    }

    public boolean submitInfomation(String ruleForm) {

        JSONObject jsonObject = JSON.parseObject(ruleForm);

        String head = jsonObject.getString("head");

        String content = jsonObject.getString("content");

        String infotime = jsonObject.getString("infotime");

        Date date = new Date();


        String userId = jsonObject.getString("userId");

        int type = jsonObject.getInteger("type");

        return adminMapper.submitInfomation(head, content, date, userId, type);

    }

    public Map<String, Object> getAllInformations(String info) {
        List<Information> information = adminMapper.getAllInformations(info);

        Map<String, Object> map = new HashMap<>();

        map.put("information", information);
        return map;
    }

    public boolean deleteInfo(int id) {
        return adminMapper.deleteInfo(id);
    }

    public boolean updateInfo(Information information) {
        return adminMapper.updateInfo(information);
    }

    public Map<String, Object> getUserList(String userid, int page, int size, String userInfo) {
        page = (page - 1) * 10;

        Buildings buildings = adminMapper.getBuilding(userid);

        List<User> users = adminMapper.getUserPage(buildings.getBid(), page, size, userInfo);

        int userCount = adminMapper.getUserCount(buildings.getBid(), userInfo);

        Map<String, Object> map = new HashMap<>();

        map.put("buildings", buildings);
        map.put("users", users);
        map.put("userCount", userCount);

        return map;
    }

    public Map<String, Object> getUserListByDate(String userid, Integer page, Integer size, String userInfo, Date date) {
        page = (page - 1) * 10;

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        date = calendar.getTime();
        String accDate = format.format(date);

        Buildings buildings = adminMapper.getBuilding(userid);

        List<User> users = adminMapper.getUserPageByDate(buildings.getBid(), page, size, userInfo, accDate);

        int userCount = adminMapper.getUserCount(buildings.getBid(), userInfo);

        Map<String, Object> map = new HashMap<>();

        map.put("buildings", buildings);
        map.put("users", users);
        map.put("userCount", userCount);

        return map;
    }

    public Map<String, Object> getAllRepairs(String userId, int page, int size, String repairInfo) {

        Buildings building = adminMapper.getBuilding(userId);

        List<Repair> repairs = adminMapper.getRepairsPage(building.getBid(), page, size, repairInfo);

        int repairCount = adminMapper.getRepairCount(building.getBid(), repairInfo);

        Map<String, Object> map = new HashMap<>();

        map.put("repairs", repairs);
        map.put("building", building);
        map.put("repairCount", repairCount);
        return map;
    }

    public boolean deleteRepair(int id) {
        return adminMapper.deleteRepair(id);
    }

    public boolean updateRepair(Repair repair) {
        return adminMapper.updateRepair(repair);
    }

    public boolean insertRecording(String addFormData) {

        JSONObject jsonObject = JSON.parseObject(addFormData);
        String userId = jsonObject.getString("userId");
        String waterFee = jsonObject.getString("waterFee");
        String electricityFee = jsonObject.getString("electricityFee");
        String propertyCosts = jsonObject.getString("propertyCosts");
        Date date = jsonObject.getDate("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String accDate = format.format(date);

        Charge charge = adminMapper.getUserByDate(userId, accDate);

        if (charge != null && charge.getId() != 0 && charge.getState() == 0) {
            return adminMapper.updateRecording(userId, waterFee, electricityFee, propertyCosts, accDate);
        } else if (charge != null && charge.getState() == 1) {
            return false;
        }
        return adminMapper.insertRecording(userId, waterFee, electricityFee, propertyCosts, date);

    }

    public boolean importRecordings(MultipartFile file) {

        List<Charge> charges = new ArrayList<>();

        try {

            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                Charge charge;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue; //标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue; //没数据
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    charge = new Charge();
                    for (int k = 0; k < physicalNumberOfCells; k++) {

                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellTypeEnum()) {
                            case STRING: {
                                String cellValue = cell.getStringCellValue();
                                if (cellValue == null) {
                                    cellValue = "";
                                }
                                switch (k) {
                                    case 0:
                                        charge.setUserId(cellValue);
                                        break;
                                    case 1:
                                        charge.setWaterFee(cellValue);
                                        break;
                                    case 2:
                                        charge.setElectricityFee(cellValue);
                                        break;
                                    case 3:
                                        charge.setPropertyCosts(cellValue);
                                        break;
                                    case 4:
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = simpleDateFormat.parse(cellValue);
                                        charge.setDate(date);
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    charges.add(charge);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        boolean flag = true;
        for(Charge charge : charges){
            String accDate = format.format(charge.getDate());

            Charge charge1 = adminMapper.getUserByDate(charge.getUserId(), accDate);

            if (charge1 != null && charge.getId() != 0 && charge.getState() == 0) {
                flag = adminMapper.updateRecording(charge.getUserId(), charge.getWaterFee(), charge.getElectricityFee(), charge.getPropertyCosts(), accDate);
            } else if (charge1 != null && charge.getState() == 1) {
                return false;
            }
            flag = adminMapper.insertRecording(charge.getUserId(), charge.getWaterFee(), charge.getElectricityFee(), charge.getPropertyCosts(), charge.getDate());

            if(flag == false){
                return false;
            }

        }

        return true;

    }


    public ResponseEntity<byte[]> exportRecordings(String userid, String date) {



        Buildings buildings = adminMapper.getBuilding(userid);

        List<User> users = adminMapper.getUserPageByDate1(buildings.getBid(), date);

        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            //创建excel文档
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            //创建摘要
            hssfWorkbook.createInformationProperties();
            //获取文档信息，并配置
            DocumentSummaryInformation documentSummaryInformation = hssfWorkbook.getDocumentSummaryInformation();
            //文档类别
            documentSummaryInformation.setCategory("用户缴费表单");
            //文档组织结构
            documentSummaryInformation.setCompany("XX物业");
            //获取摘要配置信息
            SummaryInformation summaryInformation = hssfWorkbook.getSummaryInformation();
            //设置文档主题
            summaryInformation.setSubject("用户缴费表单");
            //设置文档标题
            summaryInformation.setTitle("用户缴费表单");
            //设置文档坐着
            summaryInformation.setAuthor("XX物业");
            //设置文档备注
            summaryInformation.setComments("用户缴费表单");
            //创建excel表单
            HSSFSheet sheet = hssfWorkbook.createSheet("用户缴费表单");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = hssfWorkbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题显示样式
            HSSFCellStyle headerStyle = hssfWorkbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 15 * 256);
            sheet.setColumnWidth(1, 15 * 256);
            sheet.setColumnWidth(2, 30 * 256);
            sheet.setColumnWidth(3, 15 * 256);
            sheet.setColumnWidth(4, 20 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            //设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell1 = headerRow.createCell(0);
            cell1.setCellValue("住户id");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(1);
            cell2.setCellValue("水费");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(2);
            cell3.setCellValue("电费");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(3);
            cell4.setCellValue("物业");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(4);
            cell5.setCellValue("日期");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(5);
            cell6.setCellValue("状态");
            cell6.setCellStyle(headerStyle);
            //格式化日期
            String formatDate = null;
            //装数据
            int allScores = 0;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for(int i = 0; i < users.size(); i++){
                HSSFRow row = sheet.createRow(i + 1);
                User user = users.get(i);
                allScores = 0;
                row.createCell(0).setCellValue(user.getUserId());
                row.createCell(1).setCellValue(user.getCharge().getWaterFee());
                row.createCell(2).setCellValue(user.getCharge().getElectricityFee());
                row.createCell(3).setCellValue(user.getCharge().getPropertyCosts());
                if(user.getCharge().getDate() == null){
                    row.createCell(4).setCellValue("");
                }else{
                    String format1 = simpleDateFormat.format(user.getCharge().getDate());
                    row.createCell(4).setCellValue(format1);
                }
                if(user.getCharge().getState() == 0){
                    row.createCell(5).setCellValue("未提交");
                }else{
                    row.createCell(5).setCellValue("已提交");
                }
            }
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", new String("用户缴费表单.xls".getBytes("UTF-8"), "iso-8859-1"));
            baos = new ByteArrayOutputStream();
            hssfWorkbook.write(baos);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);

    }

    public boolean changeState(String userId, String date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date accDate = null;
        try {
            accDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return adminMapper.changeState(userId, accDate);

    }

    public List<User> getUsers() {

        Date date = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String accDate = format.format(date);

        return adminMapper.getUsers(accDate);

    }
}
