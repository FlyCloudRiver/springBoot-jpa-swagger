package com.jiang.demo.service.impl;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
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
public class UserInfoServiceImpl implements UserInfoService {

    // 通过set方法注入
    private UserInfoRepository userInfoRepository;
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo findByUsername(String username,String password) {

        return userInfoRepository.findByUsernameAndPassword(username, password);

    }


    public UserInfoDTO findByUsername(String username){
        UserInfo byUsername = userInfoRepository.findByUsername(username);

        UserInfoDTO userInfoDTO=UserInfoDTO.convert(byUsername);
        System.out.println(byUsername);
        return userInfoDTO;
    }
}
