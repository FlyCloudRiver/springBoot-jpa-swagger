package com.jiang.demo.dto.goods;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class GoodsUpdateForm {

    private Integer id;
    private  String goodsCode;

    private  String goodsName;

    private  Float goodsPrice;

    private  String goodsShelfLife;

    @ApiModelProperty(value = "商品生产日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsDate;

    private Integer categoryId;

    private Integer supplierId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
