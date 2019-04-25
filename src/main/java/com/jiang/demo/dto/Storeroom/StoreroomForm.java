package com.jiang.demo.dto.Storeroom;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class StoreroomForm {

    @ApiModelProperty(value = "库存量")
    private Integer amount;

    @ApiModelProperty(value = "库存商品")
    private Integer goodsId;

    private Date updateTime;

    @ApiModelProperty(value = "更新人员")
    private String person;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
