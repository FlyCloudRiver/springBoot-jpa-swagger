package com.jiang.demo.dto.userInfo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */
public class UserInfoForm {

    @ApiModelProperty(value = "帐号")
    private String username;//帐号

    @ApiModelProperty(value = "名称（昵称或者真实姓名）")
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    @ApiModelProperty(value = "密码")
    private String password; //密码;

    @ApiModelProperty(value = "角色列表")
    private List<Integer> roleListId;// 一个用户具有多个角色

    public List<Integer> getRoleListId() {
        return roleListId;
    }

    public void setRoleListId(List<Integer> roleListId) {
        this.roleListId = roleListId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

