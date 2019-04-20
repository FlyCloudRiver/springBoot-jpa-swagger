package com.jiang.demo.controller;

import com.jiang.demo.permission.Permission;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.supplier.SupplierForm;
import com.jiang.demo.service.SupplierService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: 江云飞
 * Date:   2019/4/16
 */


@RestController
@Api(description = "供应厂商" )   //swagger
@RequestMapping("/supplier")
public class SupplierController {

    // 通过set方法注入  优先选择
    private  SupplierService supplierService;
    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> insertSupplier(SupplierForm supplierForm){
        SupplierDTO supplierDTO = supplierService.insertSupplier(supplierForm);
        //return supplierDTO;
        return ResultUtil.success(supplierDTO);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @Permission
    public Result deleteSupplier(Integer id){
        supplierService.deleteSupplierById(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "查询ById")
    @GetMapping("/selectById")
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> selectSupplierById(Integer id){
        SupplierDTO supplierDTO = supplierService.selectSupplier(id);
        //return supplierDTO;
        return ResultUtil.success(supplierDTO);
    }

    @ApiOperation(value = "(动态分页)查询")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    public Result<PageDTO<SupplierDTO>> selectByDynamicCases(SupplierForm supplierForm, Integer pageNum, Integer pageSize){
        PageDTO<SupplierDTO> byDynamicCases = supplierService.findByDynamicCases(supplierForm, pageNum, pageSize);
        //return byDynamicCases;
        return ResultUtil.success(byDynamicCases);
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> updateSupplier(SupplierForm supplierForm, Integer id){
        SupplierDTO supplierDTO = supplierService.updateSupplier(supplierForm, id);
        //return supplierDTO;
        return ResultUtil.success(supplierDTO);
    }

}
