package com.jiang.demo.controller;


import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.dto.userInfo.UserInfoForm;
import com.jiang.demo.permission.Login;
import com.jiang.demo.permission.Permission;
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
    @Login
    @Permission
    public Result<UserInfoDTO> insertUser(@RequestBody UserInfoForm userInfoForm){
        try{
            return ResultUtil.success(userInfoService.insertUser(userInfoForm));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @Login
    @Permission
    public Result deleteUser(Integer id){
        try{
            userInfoService.deleteUser(id);
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @SuppressWarnings("unchecked")
    @Login
    @Permission
    public Result<UserInfoDTO> updateUser(@RequestBody UserInfoForm userInfoForm, Integer id){
        try{
            return ResultUtil.success(userInfoService.updateUser(userInfoForm,id));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查找所有（分页）")
    @GetMapping("/select")
    @SuppressWarnings("unchecked")
    @Login
    @Permission
    public Result<PageDTO<UserInfoDTO>>  selectUser(Integer pageNum, Integer pageSize){
        try{
            return ResultUtil.success(userInfoService.selectUser(pageNum,pageSize));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }


    @ApiOperation(value = "username查找(唯一)")
    @PostMapping("/selectByUsername")
    @SuppressWarnings("unchecked")
    @Login
    @Permission
    public Result<UserInfoDTO> selectByName(String username) {
        try{
            return ResultUtil.success(userInfoService.selectByName(username));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

}
