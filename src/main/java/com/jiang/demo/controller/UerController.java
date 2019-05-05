package com.jiang.demo.controller;


import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.dto.userInfo.UserInfoForm;
import com.jiang.demo.service.UserInfoService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: 江云飞
 * Date:   2019/4/25
 */

@RestController
@Api(description = "用户操作" )
@RequestMapping("/user")
public class UerController {

    private UserInfoService userInfoService;
    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @ApiOperation(value = "添加（用户名唯一）")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<UserInfoDTO> insertUser(@RequestBody UserInfoForm userInfoForm){
        return ResultUtil.success(userInfoService.insertUser(userInfoForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result deleteUser(Integer id){
        userInfoService.deleteUser(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @SuppressWarnings("unchecked")
    public Result<UserInfoDTO> updateUser(@RequestBody UserInfoForm userInfoForm, Integer id){

        return ResultUtil.success(userInfoService.updateUser(userInfoForm,id));
    }

    @ApiOperation(value = "查找所有（分页）")
    @GetMapping("/select")
    @SuppressWarnings("unchecked")
    public Result<PageDTO<UserInfoDTO>>  selectUser(Integer pageNum, Integer pageSize){
        System.out.println("进controller");
        return ResultUtil.success(userInfoService.selectUser(pageNum,pageSize));
    }


    @ApiOperation(value = "username查找(唯一)")
    @PostMapping("/selectByUsername")
    @SuppressWarnings("unchecked")
    public Result<UserInfoDTO> selectByName(String username) {

        return ResultUtil.success(userInfoService.selectByName(username));
    }

}
