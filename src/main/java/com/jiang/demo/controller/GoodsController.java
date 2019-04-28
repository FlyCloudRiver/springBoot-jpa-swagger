package com.jiang.demo.controller;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.service.GoodsService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(description = "商品" )   //swagger
@RequestMapping("/goods")
public class GoodsController {
    // 通过set方法注入  优先选择
    private GoodsService goodsService;
    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    //@RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result<GoodsDTO> insertGoods(GoodsForm goodsForm,String person){

        GoodsDTO goodsDTO = goodsService.insertGoods(goodsForm,person);
        //return goodsDTO;
        return ResultUtil.success(goodsDTO);
    }

    @ApiOperation(value = "动态查询")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    public Result<PageDTO<GoodsDTO>> findByDynamicCases(GoodsForm goodsForm,Integer pageNum,Integer pageSize){
        PageDTO<GoodsDTO> goodsPage=goodsService.findByDynamicCases(goodsForm,pageNum, pageSize);
        return ResultUtil.success(goodsPage);
        //return goodsPage;
    }

    @ApiOperation(value = "查询ById")
    @GetMapping("selectById")
    @SuppressWarnings("unchecked")
    public Result<GoodsDTO> selectGoodsDTOById(Integer id){
        GoodsDTO goodsDTOById = goodsService.findGoodsDTOById(id);
        //return goodsDTOById;
        return  ResultUtil.success(goodsDTOById);
    }
}
