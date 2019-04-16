package com.jiang.demo.controller;

import com.jiang.demo.config.PageDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
 * @PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
 * */
@RestController
@Api(description = "商品" )   //swagger
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public GoodsDTO insertGoods(GoodsForm goodsForm){

        Goods goods = goodsService.insertGoods(goodsForm);

        /*传进去实体类 返回 DTO类*/
        GoodsDTO convert = GoodsDTO.convert(goods);

        return convert;
    }

    @ApiOperation(value = "动态查询")
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public PageDTO<GoodsDTO> findByDynamicCases(GoodsForm goodsForm,Integer pageNum,Integer pageSize){
        PageDTO<GoodsDTO> goodsPage=goodsService.findByDynamicCases(goodsForm,pageNum, pageSize);
        System.out.println(goodsPage);

        return goodsPage;
    }

    @ApiOperation(value = "查询ById")
    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    public GoodsDTO selecteGoodsDTOById(Integer id){
        Goods goods = goodsService.findGoodsDTOById(id);
        GoodsDTO convert = GoodsDTO.convert(goods);
        return convert;
    }
}
