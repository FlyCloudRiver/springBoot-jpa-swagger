package com.jiang.demo.dto.shipment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailForm;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class ShipmentForm {

    @ApiModelProperty(value = "商品出库时间")
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//出参时间格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    private Date shipmentTime;


    @ApiModelProperty(value = "操作人员")
    private String person;


    @ApiModelProperty(value = "订单编号")
    private String shipmentCode;

    @ApiModelProperty(value = "订单详情")
    private List<ShipmentDetailForm> shipmentDetailForms;

    public String getShipmentCode() {
        return shipmentCode;
    }

    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }

    public List<ShipmentDetailForm> getShipmentDetailForms() {
        return shipmentDetailForms;
    }

    public void setShipmentDetailForms(List<ShipmentDetailForm> shipmentDetailForms) {
        this.shipmentDetailForms = shipmentDetailForms;
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
}
