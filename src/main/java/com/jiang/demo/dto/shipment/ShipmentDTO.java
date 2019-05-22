package com.jiang.demo.dto.shipment;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;
import com.jiang.demo.entity.PurchaseDetail;
import com.jiang.demo.entity.Shipment;
import com.jiang.demo.entity.ShipmentDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class ShipmentDTO {

    private Integer id;

    @ApiModelProperty(value = "订单详情")
    private List<ShipmentDetailDTO> shipmentDetailDTOS;

    @ApiModelProperty(value = "订单编号")
    private String shipmentCode;

    @ApiModelProperty(value = "商品生成时间")
    private Date createTime;

    @ApiModelProperty(value = "出库时间")
    private Date storeTime;

    @ApiModelProperty(value = "订单更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "操作人员")
    private String person;

    @ApiModelProperty(value = "是否入库")
    private Boolean isStorage;


    public Boolean getStorage() {
        return isStorage;
    }

    public void setStorage(Boolean storage) {
        isStorage = storage;
    }

    public static ShipmentDTO convert(Shipment entity) {
        ShipmentDTO dto = new ShipmentDTO();
        BeanUtils.copyProperties(entity, dto);
        //订单详情DTO
        List<ShipmentDetailDTO> shipmentDetailDTOS=new ArrayList<>();

        if(entity.getShipmentDetailList()!=null){
            //便利订单详情
            for (ShipmentDetail s:entity.getShipmentDetailList()) {

                shipmentDetailDTOS.add(ShipmentDetailDTO.convert(s));
            }
            dto.setShipmentDetailDTOS(shipmentDetailDTOS);
        }

        return dto;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ShipmentDetailDTO> getShipmentDetailDTOS() {
        return shipmentDetailDTOS;
    }

    public void setShipmentDetailDTOS(List<ShipmentDetailDTO> shipmentDetailDTOS) {
        this.shipmentDetailDTOS = shipmentDetailDTOS;
    }

    public String getShipmentCode() {
        return shipmentCode;
    }

    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
