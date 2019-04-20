package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/3/25
 */


@Entity
@ApiModel("出货单")
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID",example = "1")
    private Integer id;

    @ApiModelProperty(value = "商品")
    private Goods goods;
    @ApiModelProperty(value = "商品出库时间")
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//出参时间格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    private Date goodsInputTime;

    @ApiModelProperty(value = "出货数量")
    private Integer shipmentNumber;

    @ApiModelProperty(value = "操作人员")
    private Integer person;

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

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
