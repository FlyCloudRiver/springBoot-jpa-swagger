package com.jiang.demo.service.impl;

import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.repository.UserInfoRepository;
import com.jiang.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserInfo findByUsername(String username,String password) {
        return userInfoRepository.findByUsernameAndPassword(username,password);
    }

    private String[] admins = {"qiushi", "weixin", "xiaoshitou","admin1"};
    //是否是管理员
    public boolean isAdmin(String name) {
        return Arrays.asList(admins).contains(name);
    }

    public List<UserInfo> select(){
        List<UserInfo> all = userInfoRepository.findAll();
        for (UserInfo u:all) {
            System.out.println(u.getUid());
            System.out.println(u.getUsername());
        }

        return all;
    }
}
