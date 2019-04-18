package com.jiang.demo.controller;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/insert")
    //@RequestMapping(value = "/insert", method = RequestMethod.POST)
    public GoodsDTO insertGoods(GoodsForm goodsForm){

        GoodsDTO goodsDTO = goodsService.insertGoods(goodsForm);
        return goodsDTO;
    }

    @ApiOperation(value = "动态查询")
    @PostMapping("/select")
    public PageDTO<GoodsDTO> findByDynamicCases(GoodsForm goodsForm,Integer pageNum,Integer pageSize){
        PageDTO<GoodsDTO> goodsPage=goodsService.findByDynamicCases(goodsForm,pageNum, pageSize);
        System.out.println(goodsPage);

        return goodsPage;
    }

    @ApiOperation(value = "查询ById")
    @GetMapping("selectById")
    public GoodsDTO selectGoodsDTOById(Integer id){
        GoodsDTO goodsDTOById = goodsService.findGoodsDTOById(id);
        return goodsDTOById;
    }
}
