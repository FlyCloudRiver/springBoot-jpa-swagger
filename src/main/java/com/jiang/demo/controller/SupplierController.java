package com.jiang.demo.controller;

import com.jiang.demo.config.PageDTO;
import com.jiang.demo.dto.Supplier.SupplierDTO;
import com.jiang.demo.dto.Supplier.SupplierForm;
import com.jiang.demo.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public SupplierDTO insertSupplier(SupplierForm supplierForm){
        SupplierDTO supplierDTO = supplierService.insertSupplier(supplierForm);
        return supplierDTO;
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteSupplier(Integer id){
        supplierService.deleteSupplierById(id);
    }

    @ApiOperation(value = "查询ById")
    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    public SupplierDTO selectSupplierById(Integer id){
        SupplierDTO supplierDTO = supplierService.selectSupplier(id);
        return supplierDTO;
    }

    @ApiOperation(value = "(动态分页)查询")
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public PageDTO<SupplierDTO> selectByDynamicCases(SupplierForm supplierForm, Integer pageNum, Integer pageSize){
        PageDTO<SupplierDTO> byDynamicCases = supplierService.findByDynamicCases(supplierForm, pageNum, pageSize);
        return byDynamicCases;
    }

    @ApiOperation(value = "更新")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public SupplierDTO updateSupplier(SupplierForm supplierForm, Integer id){
        SupplierDTO supplierDTO = supplierService.updateSupplier(supplierForm, id);
        return supplierDTO;
    }

}
