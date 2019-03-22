package com.jiang.demo.controller;

import com.jiang.demo.service.CommodityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "厂商" )   //swagger
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;
}
