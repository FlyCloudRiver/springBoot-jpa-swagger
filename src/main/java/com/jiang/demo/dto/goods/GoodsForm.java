package com.jiang.demo.dto.goods;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class GoodsForm {

    @ApiModelProperty(value = "商品编号")
    private  String goodsCode;

    @ApiModelProperty(value = "商品名称")
    private  String goodsName;

    @ApiModelProperty(value = "商品售价(元)")
    private  Float goodsPrice;

    @ApiModelProperty(value = "商品进价(元)")
    private  Float purchasePrice;

    @ApiModelProperty(value = "商品规格型号")
    private  String goodsSpecification;

    @ApiModelProperty(value = "商品计量单位(桶、包、个、瓶......)")
    private  String goodsUnit;

    @ApiModelProperty(value = "细类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "厂商ID")
    private Integer supplierId;

    private Integer pageNum;
    private Integer pageSize;

    @ApiModelProperty(value = "添加商品的操作人")
    private String person;

    @ApiModelProperty(value = "厂商名字（查询）")
    private String supplierName;

    @ApiModelProperty(value = "商品细类（查询）")
    private String categoryName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
}
