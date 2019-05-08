package com.jiang.demo.service;


import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.shipment.ShipmentDTO;
import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/25
 */
public interface ShipmentDetailService {
    List<ShipmentDetailDTO> insertShipmentDetail(ShipmentForm shipmentForm);
    void update(ShipmentDTO shipmentDTO);
    ShipmentDetailDTO selectDetail(Integer id);
}
