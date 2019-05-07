package com.jiang.demo.controller;

import com.jiang.demo.dto.Storeroom.StoreroomDTO;
import com.jiang.demo.dto.Storeroom.StoreroomForm;
import com.jiang.demo.dto.purchase.PurchaseStorageFrom;
import com.jiang.demo.dto.shipment.ShipmentStorageFrom;
import com.jiang.demo.service.StoreroomService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */

@RestController
@Api(description = "商品库存管理" )   //swagger
@RequestMapping("/storeroom")
public class StoreroomController {

    private StoreroomService storeroomService;

    @Autowired
    public void setStoreroomService(StoreroomService storeroomService) {
        this.storeroomService = storeroomService;
    }

    @ApiOperation(value = "商品库存动态分页显示")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<List<StoreroomDTO>> select(@RequestBody StoreroomForm storeroomForm) {
        PageDTO<StoreroomDTO> select = storeroomService.select(storeroomForm);
        return ResultUtil.success(select);
    }


    @ApiOperation(value = "商品进库")
    @PostMapping("/insert")
    public Result insertStorage(@RequestBody PurchaseStorageFrom purchaseStorageFrom) {
        storeroomService.insertStorage(purchaseStorageFrom);
        return ResultUtil.success();
    }

    @ApiOperation(value = "商品出库")
    @PostMapping("/output")
    public Result outputStorage(@RequestBody ShipmentStorageFrom shipmentStorageFrom) {
        storeroomService.outputStorage(shipmentStorageFrom);
        return ResultUtil.success();
    }

}
