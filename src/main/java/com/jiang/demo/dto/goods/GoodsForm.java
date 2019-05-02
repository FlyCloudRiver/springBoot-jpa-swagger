package com.jiang.demo.dto.goods;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

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
    @ApiModelProperty(value = "商品单价")
    private  Float goodsPrice;


    @ApiModelProperty(value = "商品保质期")
    private  String goodsShelfLife;

    @ApiModelProperty(value = "商品生产日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsDate;

    @ApiModelProperty(value = "细类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "厂商ID")
    private Integer supplierId;

    private Integer pageNum;
    private Integer pageSize;
    private String person;

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


    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
    }

    public String getGoodsShelfLife() {
        return goodsShelfLife;
    }

    public void setGoodsShelfLife(String goodsShelfLife) {
        this.goodsShelfLife = goodsShelfLife;
    }


    @Override
    public String toString() {
        return "GoodsForm{" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsShelfLife=" + goodsShelfLife +
                '}';
    }
}
