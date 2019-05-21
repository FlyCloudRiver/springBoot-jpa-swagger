package com.jiang.demo.dto.reportForm;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Author: 江云飞
 * Date:   2019/5/10
 */

public class ReportDTO {

    @ApiModelProperty(value = "商品ID")

    private Integer goodId;

    @ApiModelProperty(value = "商品名")

    private String goodsName;

    @ApiModelProperty(value = "商品进货出货进货量")

    private Integer num;



    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
