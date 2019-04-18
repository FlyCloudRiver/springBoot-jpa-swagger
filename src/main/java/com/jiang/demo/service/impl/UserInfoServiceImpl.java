package com.jiang.demo.service.impl;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.entity.SysRole;
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
        UserInfo byUsernameAndPassword = userInfoRepository.findByUsernameAndPassword(username, password);


        return byUsernameAndPassword;

    }

    private String[] admins = {"qiushi", "weixin", "xiaoshitou","admin1"};
    //是否是管理员
    public boolean isAdmin(String name) {
        return Arrays.asList(admins).contains(name);
    }

    public UserInfoDTO findByUsername(String username){
        UserInfo byUsername = userInfoRepository.findByUsername(username);

        UserInfoDTO userInfoDTO=UserInfoDTO.convert(byUsername);
        System.out.println(byUsername);
        return userInfoDTO;
    }
}
