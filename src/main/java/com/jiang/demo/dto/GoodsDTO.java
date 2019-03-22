package com.jiang.demo.dto;

import com.jiang.demo.entity.Commodity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel("商品DTO")
public class GoodsDTO {


    @ApiModelProperty("商品ID")
    private Long id;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品价格")
    private Float goodsPrice;

    @ApiModelProperty("厂商DTO")
    private Commodity Commodity;//所属厂商

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public com.jiang.demo.entity.Commodity getCommodity() {
        return Commodity;
    }

    public void setCommodity(com.jiang.demo.entity.Commodity commodity) {
        Commodity = commodity;
    }
}
