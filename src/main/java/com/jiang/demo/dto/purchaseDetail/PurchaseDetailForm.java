package com.jiang.demo.dto.purchaseDetail;

import io.swagger.annotations.ApiModelProperty;

/**
 * Author: 江云飞
 * Date:   2019/4/22
 */
public class PurchaseDetailForm {

    @ApiModelProperty(value = "商品")
    private Integer goodsId;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;


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
}
