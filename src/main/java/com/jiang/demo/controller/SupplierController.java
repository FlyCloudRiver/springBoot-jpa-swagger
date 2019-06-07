package com.jiang.demo.controller;

import com.jiang.demo.permission.Login;
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

import java.util.List;

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
    @Login
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> insertSupplier(@RequestBody SupplierForm supplierForm){
        try{
            SupplierDTO supplierDTO = supplierService.insertSupplier(supplierForm);
            //return supplierDTO;
            return ResultUtil.success(supplierDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @Login
    @Permission
    public Result deleteSupplier(Integer id){
        try{
            supplierService.deleteSupplierById(id);
            return ResultUtil.success();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询ById")
    @PostMapping("/selectById")
    @Login
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> selectSupplierById(@RequestBody Integer id){
        try{
            SupplierDTO supplierDTO = supplierService.selectSupplier(id);
            //return supplierDTO;
            return ResultUtil.success(supplierDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "(动态分页)查询")
    @PostMapping("/select")
    @Login
    @SuppressWarnings("unchecked")
    public Result<PageDTO<SupplierDTO>> selectByDynamicCases(@RequestBody SupplierForm supplierForm){
        try{
            PageDTO<SupplierDTO> byDynamicCases = supplierService.findByDynamicCases(supplierForm);
            //return byDynamicCases;
            return ResultUtil.success(byDynamicCases);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    @Login
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> updateSupplier(@RequestBody SupplierForm supplierForm, Integer id){
        try{
            SupplierDTO supplierDTO = supplierService.updateSupplier(supplierForm, id);
            //return supplierDTO;
            return ResultUtil.success(supplierDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询所有")
    @PostMapping("/selectAll")
    @Login
    @SuppressWarnings("unchecked")
    public Result<SupplierDTO> selectAll(){
        try{
            List<SupplierDTO> all = supplierService.selectAll();
            return ResultUtil.success(all);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResultUtil.error(-1,e.getMessage());
        }

    }
}
