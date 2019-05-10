package com.jiang.demo.dto.Storeroom;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class StoreroomForm {

    private Integer pageNum;
    private Integer pageSize;
    @ApiModelProperty(value = "库存量")
    private Integer amount;

    @ApiModelProperty(value = "商品编号")
    private  String goodsCode;

    @ApiModelProperty(value = "商品名称")
    private  String goodsName;


    @ApiModelProperty(value = "更新人员")
    private String person;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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



    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
