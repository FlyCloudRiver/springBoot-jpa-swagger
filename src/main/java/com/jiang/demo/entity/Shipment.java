package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/3/25
 */
/*
* 单项一对多*/

@Entity
@ApiModel("销售单")
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID",example = "1")
    private Integer id;

    @ApiModelProperty(value = "订单详情")
    @OneToMany(mappedBy = "shipment",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<ShipmentDetail> shipmentDetailList;

    @ApiModelProperty(value = "订单编号")
    private String shipmentCode;

    @ApiModelProperty(value = "订单生成时间")
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//出参时间格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    private Date shipmentTime;

    @ApiModelProperty(value = "是否入库")
    private Boolean isStorage=false;

    @ApiModelProperty(value = "操作人员")
    private String person;

    public Boolean getStorage() {
        return isStorage;
    }

    public void setStorage(Boolean storage) {
        isStorage = storage;
    }

    public String getShipmentCode() {
        return shipmentCode;
    }

    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }

    public Date getShipmentTime() {
        return shipmentTime;
    }

    public void setShipmentTime(Date shipmentTime) {
        this.shipmentTime = shipmentTime;
    }



    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public List<ShipmentDetail> getShipmentDetailList() {
        return shipmentDetailList;
    }

    public void setShipmentDetailList(List<ShipmentDetail> shipmentDetailList) {
        this.shipmentDetailList = shipmentDetailList;
    }
}
