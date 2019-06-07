package com.jiang.demo.controller;


import com.jiang.demo.dto.Storeroom.StoreroomDTO;
import com.jiang.demo.dto.Storeroom.StoreroomForm;

import com.jiang.demo.dto.purchase.PurchaseStorageFrom;
import com.jiang.demo.dto.shipment.ShipmentStorageFrom;
import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.permission.Login;
import com.jiang.demo.service.StoreroomService;
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
    @Login
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<List<StoreroomDTO>> select(@RequestBody StoreroomForm storeroomForm) {
        try{
            PageDTO<StoreroomDTO> select = storeroomService.select(storeroomForm);
            return ResultUtil.success(select);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }


    @ApiOperation(value = "商品进库")
    @PostMapping("/insert")
    @Login
    public Result insertStorage(@RequestBody PurchaseStorageFrom purchaseStorageFrom) {
        try{
            storeroomService.insertStorage(purchaseStorageFrom);
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "商品出库")
    @PostMapping("/output")
    @Login
    public Result outputStorage(@RequestBody ShipmentStorageFrom shipmentStorageFrom) {
        try{
            storeroomService.outputStorage(shipmentStorageFrom);
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询库存(分页)")
    @PostMapping("/selectAll")
    @Login
    @SuppressWarnings("unchecked")
    public  Result<PageDTO<StoreroomDTO>> selectAll(Integer pageNum,Integer pageSize){
        PageDTO<StoreroomDTO> storeroomDTOPageDTO = storeroomService.selectAll(pageNum, pageSize);
        return ResultUtil.success(storeroomDTOPageDTO);
    }

    @ApiOperation(value = "根据商品id查询交易记录")
    @PostMapping("/selectInfo")
    @Login
    @SuppressWarnings("unchecked")
    //根据商品id查询交易记录
    public Result<List<Storeroom>> selectInfo(Integer goodsId){
        List<Storeroom> storeroomList = storeroomService.selectInfo(goodsId);
        return ResultUtil.success(storeroomList);
    }

}
