package com.jiang.demo.dto.Supplier;

import com.jiang.demo.entity.Supplier;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;



/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class SupplierDTO {

    private Integer id;

    @ApiModelProperty(value = "供应商商号")
    private  String supplierCode;

    @ApiModelProperty(value = "供应商名")
    private  String supplierName;

    @ApiModelProperty(value = "供应商电话")
    private  String supplierPhone;

    @ApiModelProperty(value = "供应商官网")
    private  String supplierWeb;

    @ApiModelProperty(value = "供应商地址")
    private  String supplierAddress;

    public static SupplierDTO convert(Supplier entity) {
        SupplierDTO dto = new SupplierDTO();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierWeb() {
        return supplierWeb;
    }

    public void setSupplierWeb(String supplierWeb) {
        this.supplierWeb = supplierWeb;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
}
