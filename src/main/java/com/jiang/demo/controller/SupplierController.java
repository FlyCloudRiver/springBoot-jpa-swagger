package com.jiang.demo.controller;

import com.jiang.demo.permission.Permission;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.supplier.SupplierForm;
import com.jiang.demo.service.SupplierService;
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

    @Autowired
    SupplierService supplierService;

    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    public SupplierDTO insertSupplier(SupplierForm supplierForm){
        SupplierDTO supplierDTO = supplierService.insertSupplier(supplierForm);
        return supplierDTO;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @Permission
    public void deleteSupplier(Integer id){
        supplierService.deleteSupplierById(id);
    }

    @ApiOperation(value = "查询ById")
    @GetMapping("/selectById")
    public SupplierDTO selectSupplierById(Integer id){
        SupplierDTO supplierDTO = supplierService.selectSupplier(id);
        return supplierDTO;
    }

    @ApiOperation(value = "(动态分页)查询")
    @PostMapping("/select")
    public PageDTO<SupplierDTO> selectByDynamicCases(SupplierForm supplierForm, Integer pageNum, Integer pageSize){
        PageDTO<SupplierDTO> byDynamicCases = supplierService.findByDynamicCases(supplierForm, pageNum, pageSize);
        return byDynamicCases;
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    public SupplierDTO updateSupplier(SupplierForm supplierForm, Integer id){
        SupplierDTO supplierDTO = supplierService.updateSupplier(supplierForm, id);
        return supplierDTO;
    }

}
