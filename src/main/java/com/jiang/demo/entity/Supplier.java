package com.jiang.demo.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "supplier")
/*供应商*/
@ApiModel("供应厂商")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "供应商ID",example = "1")
    private Integer id;

    @ApiModelProperty(value = "供应商名")
    @Column(name = "supplier_name")
    private  String supplierName;

    @ApiModelProperty(value = "供应商电话")
    @Column(name = "supplier_phone")
    private  String supplierPhone;

    @ApiModelProperty(value = "供应商官网")
    @Column(name = "supplier_web")
    private  String supplierWeb;

    @ApiModelProperty(value = "供应商地址")
    @Column(name = "supplier_address")
    private  String supplierAddress;


    @ApiModelProperty(value = "商品")
    @OneToMany(mappedBy = "supplier",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Goods> goodsList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }


}
