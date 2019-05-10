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


    @ApiModelProperty(value = "是否入库")
    private Boolean isStorage;

    @ApiModelProperty(value = "操作人员")
    private String person;


    @ApiModelProperty(value = "订单编号")
    private String shipmentCode;

    @ApiModelProperty(value = "订单详情")
    private List<ShipmentDetailForm> shipmentDetailForms;

    private Integer pageNum;
    private Integer pageSize;

    public Boolean getStorage() {
        return isStorage;
    }

    public void setStorage(Boolean storage) {
        isStorage = storage;
    }

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




    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
