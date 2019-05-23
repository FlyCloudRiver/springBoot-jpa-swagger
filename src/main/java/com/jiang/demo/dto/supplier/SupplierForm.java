package com.jiang.demo.dto.supplier;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;


/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class SupplierForm {

    @ApiModelProperty(value = "供应商名")
    private  String supplierName;

    @ApiModelProperty(value = "供应商电话")
    private  String supplierPhone;

    @ApiModelProperty(value = "供应商官网")
    private  String supplierWeb;

    @ApiModelProperty(value = "供应商地址")
    private  String supplierAddress;

    @ApiModelProperty(value = "供应商详细地址")
    private  String supplierAddressDetail;



    private Integer pageNum;
    private Integer pageSize;

    public String getSupplierAddressDetail() {
        return supplierAddressDetail;
    }

    public void setSupplierAddressDetail(String supplierAddressDetail) {
        this.supplierAddressDetail = supplierAddressDetail;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
