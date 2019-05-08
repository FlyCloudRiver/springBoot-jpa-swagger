package com.jiang.demo.controller;


import com.jiang.demo.dto.shipment.ShipmentDTO;
import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;
import com.jiang.demo.service.ShipmentDetailService;
import com.jiang.demo.service.ShipmentService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */


@RestController
@Api(description = "销售单管理" )   //swagger
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
    @ApiOperation(value = "生成销售单")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<List<ShipmentDetailDTO>> insertShipment(@RequestBody ShipmentForm shipmentForm){

        //然后添加订单详情
        List<ShipmentDetailDTO> shipmentDetailDTOS = shipmentDetailService.insertShipmentDetail(shipmentForm);

        return ResultUtil.success(shipmentDetailDTOS);
    }

    @ApiOperation(value = "销售单动态分页显示")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<List<ShipmentDTO>> select(@RequestBody ShipmentForm purchaseForm){
        PageDTO<ShipmentDTO> select = shipmentService.select(purchaseForm);
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "更新采购单")
    @PutMapping("/update")
    public Result update(@RequestBody ShipmentDTO shipmentDTO){
        //参数  采购单详情id  以及对应的商品的数量
        shipmentDetailService.update(shipmentDTO);
        return ResultUtil.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@Permission
    public Result delete(Integer id){
        shipmentService.delete(id);
        return ResultUtil.success();
    }


    @ApiOperation(value = "获取销售单详情")
    @PostMapping("/selectDetail")
    @SuppressWarnings("unchecked")
    public Result<ShipmentDetailDTO> selectDetail(Integer id){
        ShipmentDetailDTO shipmentDetailDTO = shipmentDetailService.selectDetail(id);
        return ResultUtil.success(shipmentDetailDTO);
    }
}
