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


    @ApiModelProperty(value = "商品数量")
    private  Integer goodsNumber;

    @ApiModelProperty(value = "商品保质期")
    private  Integer goodsShelfLife;

    @ApiModelProperty(value = "商品生产日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsDate;

    @ApiModelProperty(value = "细类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "厂商ID")
    private Integer supplierId;

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


    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsShelfLife() {
        return goodsShelfLife;
    }

    public void setGoodsShelfLife(Integer goodsShelfLife) {
        this.goodsShelfLife = goodsShelfLife;
    }

    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
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

    @Override
    public String toString() {
        return "GoodsForm{" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNumber=" + goodsNumber +
                ", goodsShelfLife=" + goodsShelfLife +
                ", goodsDate=" + goodsDate +
                ", categoryId=" + categoryId +
                ", supplierId=" + supplierId +
                '}';
    }
}
