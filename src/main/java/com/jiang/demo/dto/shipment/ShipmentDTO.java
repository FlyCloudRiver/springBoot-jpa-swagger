package com.jiang.demo.dto.shipment;


import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;
import com.jiang.demo.entity.PurchaseDetail;
import com.jiang.demo.entity.Shipment;
import com.jiang.demo.entity.ShipmentDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

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

    @ApiModelProperty(value = "商品出库时间")
    private Date goodsInputTime;

    @ApiModelProperty(value = "操作人员")
    private String person;

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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getGoodsInputTime() {
        return goodsInputTime;
    }

    public void setGoodsInputTime(Date goodsInputTime) {
        this.goodsInputTime = goodsInputTime;
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
