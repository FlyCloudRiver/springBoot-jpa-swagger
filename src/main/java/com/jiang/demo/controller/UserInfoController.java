package com.jiang.demo.controller;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.entity.SysRole;
import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.permission.Permission;
import com.jiang.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */

@RestController
@Api(description = "用户" )
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public UserInfoDTO login(String username, String password){
        UserInfo byUsername = userInfoService.findByUsername(username, password);

        List<SysRole> roleList = byUsername.getRoleList();

        UserInfoDTO userInfoDTO=UserInfoDTO.convert(byUsername);

        return userInfoDTO;
    }





}
