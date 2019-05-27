package com.jiang.demo.controller;


import com.jiang.demo.entity.Area;
import com.jiang.demo.entity.City;
import com.jiang.demo.entity.Province;
import com.jiang.demo.service.ProvinceService;
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
 * Date:   2019/5/6
 */

@RestController
@Api(description = "省份" )
@RequestMapping("/province")
public class ProvinceController {

    private ProvinceService provinceService;
    @Autowired
    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @ApiOperation(value = "查找省份")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    public Result<List<Province>> select(){
        return ResultUtil.success(provinceService.select());
    }

    @ApiOperation(value = "查找市")
    @PostMapping("/selectCity")
    @SuppressWarnings("unchecked")
    public Result<List<City>> selectCity(String procinceid){
        return ResultUtil.success(provinceService.selectCity(procinceid));
    }

    @ApiOperation(value = "查找县区")
    @PostMapping("/selectArea")
    @SuppressWarnings("unchecked")
    public Result<List<Area>> selectArea(String cityid){
        return ResultUtil.success(provinceService.selectArea(cityid));
    }
}
