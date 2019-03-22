package com.jiang.demo.controller;

import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.User;
import com.jiang.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "商品" )   //swagger
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加商品")
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public Goods insertuser(Goods goods){
        Goods save = goodsService.insertGoods(goods);
        return save;
    }
}
