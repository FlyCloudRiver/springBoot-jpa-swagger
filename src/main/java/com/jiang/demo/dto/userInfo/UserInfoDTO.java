package com.jiang.demo.dto.userInfo;


import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.sysPermission.SysPermissionDTO;
import com.jiang.demo.dto.sysRole.SysRoleDTO;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.entity.SysPermission;
import com.jiang.demo.entity.SysRole;
import com.jiang.demo.entity.UserInfo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */
public class UserInfoDTO {

    private Integer uid;
    @ApiModelProperty(value = "账号")
    private String username;//帐号

    @ApiModelProperty(value = "名称（昵称或者真实姓名）")
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    private List<SysRoleDTO> roleDTOS;//角色
    public static UserInfoDTO convert(UserInfo entity) {
        UserInfoDTO dto = new UserInfoDTO();
        BeanUtils.copyProperties(entity, dto);


        List<SysRoleDTO> s=new ArrayList<>();
        if(entity.getRoleList()!=null){
            for (SysRole sr:entity.getRoleList()) {
                s.add(SysRoleDTO.convert(sr));
            }
            dto.setRoleDTOS(s);
        }
        return dto;
    }

    public List<SysRoleDTO> getRoleDTOS() {
        return roleDTOS;
    }

    public void setRoleDTOS(List<SysRoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
}
