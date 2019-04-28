package com.jiang.demo.service;


import com.jiang.demo.dto.shipment.ShipmentDTO;
import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.utils.PageDTO;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public interface ShipmentService {

    //动态分页查询（编号，时间，姓名）
    PageDTO<ShipmentDTO> select(ShipmentForm purchaseForm, Integer pageNum, Integer pageSize);
}
