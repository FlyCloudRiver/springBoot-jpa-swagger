package com.jiang.demo.controller;

import com.jiang.demo.dto.CommodityDTO;
import com.jiang.demo.dto.CommodityForm;
import com.jiang.demo.entity.Commodity;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "厂商" )   //swagger
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @ApiOperation(value = "添加厂商")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Integer insertCommodity(CommodityForm commodityForm){
        Integer save = commodityService.insertCommodity(commodityForm);
        return save;
    }


}
