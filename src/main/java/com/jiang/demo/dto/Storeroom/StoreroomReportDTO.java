package com.jiang.demo.dto.Storeroom;

import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.entity.Storeroom;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/5/27
 */
public class StoreroomReportDTO {

    private Integer id;

    @ApiModelProperty(value = "库存量")
    private Integer amount;

    @ApiModelProperty(value = "库存商品")
    private GoodsDTO goodsDTO;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人员")
    private String person;

    @ApiModelProperty(value = "出入库类型")
    private String style;


    //新增
    @ApiModelProperty(value = "出库或者入库总数")
    private Integer totleNumber;

    @ApiModelProperty(value = "出库或者入库总金额")
    private Float sumMoney;


    public static StoreroomReportDTO convert(Storeroom entity) {
        StoreroomReportDTO dto = new StoreroomReportDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getGoods()!=null){
            dto.setGoodsDTO(GoodsDTO.convert(entity.getGoods()));
        }

        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getTotleNumber() {
        return totleNumber;
    }

    public void setTotleNumber(Integer totleNumber) {
        this.totleNumber = totleNumber;
    }

    public Float getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Float sumMoney) {
        this.sumMoney = sumMoney;
    }
}
