package com.management.wuye.controller;

import com.alibaba.fastjson.JSON;
import com.management.wuye.bean.Information;
import com.management.wuye.bean.User;
import com.management.wuye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Value("${file.rootPath}")
    private String ROOT_PATH;
    //图片存放根目录下的子目录
    @Value("${file.sonPath}")
    private String SON_PATH;

    @RequestMapping(value = "/user/addUser",method = RequestMethod.POST)
    public Boolean addUser(@RequestParam("user") String info){
        User user = JSON.parseObject(info,User.class);
        System.out.println(user.toString());
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user/deleteUser",method = RequestMethod.DELETE)
    public Boolean deleteUser(@RequestParam("userId")String userId){
        System.out.println(userId);
        return userService.deleteUser(userId);
    }

    @RequestMapping(value = "/user/updateUser",method = RequestMethod.POST)
    public Boolean updateUser(@RequestParam("user")String info){
        User user = JSON.parseObject(info,User.class);
        System.out.println(user.toString());
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user/getUserByName",method = RequestMethod.POST)
    public int getUserByName(@RequestParam("userName")String userName){
        System.out.println(userName);
        return userService.getUserByName(userName);
    }

    @RequestMapping("/userface/upload")
    public boolean userfaceUpload(String userId, MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return false;
        }


        String fileName = file.getOriginalFilename();
        String suffixName  = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;   //新文件名
        String userFileDir = userId;
        File dest = new File(ROOT_PATH + SON_PATH + userFileDir + '/'+  fileName);
        System.out.println("================================================" + dest.getParentFile());
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        deleteDirectory(dest.getParentFile());
        file.transferTo(dest);

        String path = SON_PATH  + userFileDir + '/' + fileName;
        boolean result = userService.changeUserfaceById(userId, path);

        if(result) {
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/userface/get")
    public String getUserfaceById(String userId){
        return userService.getUserfaceById(userId);
    }

    @RequestMapping("/publicChat")
    public Boolean  publicChat(@RequestParam("chat") String chat, @RequestParam("id") int id){

        return userService.publicChat(chat, id);

    }
    @RequestMapping("/getInfomation")
    public Information publicChat(@RequestParam("id") int id){

        return userService.publicChat1(id);

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
