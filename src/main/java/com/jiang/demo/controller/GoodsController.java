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
    public Result<GoodsDTO> insertGoods(@RequestBody GoodsForm goodsForm){

        try{
            GoodsDTO goodsDTO = goodsService.insertGoods(goodsForm);
            return ResultUtil.success(goodsDTO);
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "分页动态查询")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    public Result<PageDTO<GoodsDTO>> findByDynamicCases(@RequestBody GoodsForm goodsForm){
        try{
            PageDTO<GoodsDTO> goodsPage=goodsService.findByDynamicCases(goodsForm);
            return ResultUtil.success(goodsPage);
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询ById")
    @PostMapping("selectById")
    @SuppressWarnings("unchecked")
    public Result<GoodsDTO> selectGoodsDTOById(Integer id){

        try{
            GoodsDTO goodsDTOById = goodsService.findGoodsDTOById(id);
            return  ResultUtil.success(goodsDTOById);
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    @SuppressWarnings("unchecked")
    public Result<GoodsDTO> updateGoods(@RequestBody GoodsDTO goodsDTO){
        try{
            GoodsDTO goodsDTOById = goodsService.updateGoods(goodsDTO);
            return  ResultUtil.success(goodsDTOById);
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@Permission
    public Result deleteGoods(Integer id){
        try{
            goodsService.deleteGoods(id);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }
}
