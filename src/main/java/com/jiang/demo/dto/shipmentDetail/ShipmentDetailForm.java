package com.jiang.demo.dto.shipmentDetail;

import io.swagger.annotations.ApiModelProperty;

/**
 * Author: 江云飞
 * Date:   2019/4/25
 */
public class ShipmentDetailForm {

    @ApiModelProperty(value = "商品")
    private Integer goodsId;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "总库存")
    private Integer goodsAmount;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }
}
