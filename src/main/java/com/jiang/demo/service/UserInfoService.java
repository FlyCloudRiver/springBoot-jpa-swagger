package com.jiang.demo.service;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.entity.UserInfo;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */
public interface UserInfoService {

    UserInfo findByUsername(String username, String password);

    UserInfoDTO findByUsername(String username);
}
