package com.jiang.demo.service;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.dto.userInfo.UserInfoForm;
import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.utils.PageDTO;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */
public interface UserInfoService {

    UserInfo login(String username, String password);

    UserInfoDTO insertUser(UserInfoForm userInfoForm);

    void deleteUser(Integer id);

    UserInfoDTO updateUser(UserInfoForm userInfoForm,Integer id);

    PageDTO<UserInfoDTO>  selectUser(Integer pageNum, Integer pageSize);

    UserInfoDTO selectByName(String username) ;
}
