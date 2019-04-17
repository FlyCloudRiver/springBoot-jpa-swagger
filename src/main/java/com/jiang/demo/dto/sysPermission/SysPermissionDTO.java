package com.jiang.demo.dto.sysPermission;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.entity.SysPermission;
import com.jiang.demo.entity.UserInfo;
import org.springframework.beans.BeanUtils;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */
public class SysPermissionDTO {

    private Integer id;//主键.

    private String name;//名称.

    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    public static SysPermissionDTO convert(SysPermission entity) {
        SysPermissionDTO dto = new SysPermissionDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
