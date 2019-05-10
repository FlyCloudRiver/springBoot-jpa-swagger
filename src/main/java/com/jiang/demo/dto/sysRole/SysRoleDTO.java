package com.jiang.demo.dto.sysRole;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.sysPermission.SysPermissionDTO;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.entity.SysPermission;
import com.jiang.demo.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */
public class SysRoleDTO {

    private Integer id; // 编号

    @ApiModelProperty(value = "角色名")
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

    @ApiModelProperty(value = "描述")
    private String description; // 角色描述,UI界面显示使用

    @ApiModelProperty(value = "权限列表")
    private List<SysPermissionDTO> permissionDTOS;


    public static SysRoleDTO convert(SysRole entity) {
        SysRoleDTO dto = new SysRoleDTO();
        BeanUtils.copyProperties(entity, dto);

        List<SysPermissionDTO> s=new ArrayList<>();
        if(entity.getPermissions()!=null){
            for (SysPermission p:entity.getPermissions()) {
                s.add(SysPermissionDTO.convert(p));
            }
            dto.setPermissionDTOS(s);
        }
        return dto;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysPermissionDTO> getPermissionDTOS() {
        return permissionDTOS;
    }

    public void setPermissionDTOS(List<SysPermissionDTO> permissionDTOS) {
        this.permissionDTOS = permissionDTOS;
    }
}
