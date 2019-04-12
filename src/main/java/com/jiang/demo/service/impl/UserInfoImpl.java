package com.jiang.demo.service.impl;

import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.repository.UserInfoRepository;
import com.jiang.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */

@Service
public class UserInfoImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserInfo findByUsername(String username,String password) {
        return userInfoRepository.findByUsernameAndPassword(username,password);
    }
}
