package com.jiang.demo.controller;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.shipment.ShipmentDTO;
import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;
import com.jiang.demo.entity.ShipmentDetail;
import com.jiang.demo.service.ShipmentDetailService;
import com.jiang.demo.service.ShipmentService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */


@RestController
@Api(description = "商品出货" )   //swagger
@RequestMapping("/shipment")
public class ShipmentController {
    private ShipmentService shipmentService;
    @Autowired
    public void setShipmentService(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    private ShipmentDetailService shipmentDetailService;
    @Autowired
    public void setShipmentDetailService(ShipmentDetailService shipmentDetailService) {
        this.shipmentDetailService = shipmentDetailService;
    }

    /*提交订单详情的集合  以及订单*/
    @ApiOperation(value = "出售商品")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<List<ShipmentDetailDTO>> insertShipment(ShipmentForm shipmentForm){

        //然后添加订单详情
        List<ShipmentDetailDTO> shipmentDetailDTOS = shipmentDetailService.insertShipmentDetail(shipmentForm);

        return ResultUtil.success(shipmentDetailDTOS);
    }

    @ApiOperation(value = "销售单动态分页显示")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<List<ShipmentDTO>> select(ShipmentForm purchaseForm, Integer pageNum, Integer pageSize){
        PageDTO<ShipmentDTO> select = shipmentService.select(purchaseForm, pageNum, pageSize);
        return ResultUtil.success(select);
    }
}
