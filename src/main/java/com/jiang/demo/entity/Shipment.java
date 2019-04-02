package com.jiang.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/3/25
 */


@Entity
@ApiModel("出库")
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID",example = "1")
    private Integer id;


    @ApiModelProperty(value = "商品")
    private Goods goods;

    @ApiModelProperty(value = "商品出库时间")
    private Date goodsInputTime;

    @ApiModelProperty(value = "出货数量")
    private Integer shipmentNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Date getGoodsInputTime() {
        return goodsInputTime;
    }

    public void setGoodsInputTime(Date goodsInputTime) {
        this.goodsInputTime = goodsInputTime;
    }

    public Integer getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(Integer shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }
}
