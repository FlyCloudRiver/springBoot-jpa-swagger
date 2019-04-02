package com.jiang.demo.controller;
import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.service.SecondaryCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */

@RestController
@Api(description = "商品中类" )   //swagger
@RequestMapping("/secondaryCategory")
public class SecondaryCategoryController {
    @Autowired
    private SecondaryCategoryService secondaryCategoryService;

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public SecondaryCategoryDTO insertSecondaryCategory(Integer bigCategoryId,String secondaryCategoryName){

        SecondaryCategory entity = secondaryCategoryService.insertSecondaryCategory(bigCategoryId,secondaryCategoryName);
        return SecondaryCategoryDTO.convert(entity);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteSecondaryCategory(Integer id){
        secondaryCategoryService.deleteSecondaryCategoryById(id);

    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public SecondaryCategoryDTO updateSecondaryCategory(Integer id,String secondaryCategoryName,Integer bigCategoryId){

        SecondaryCategory secondaryCategory1 = secondaryCategoryService.updateSecondaryCategory(id,secondaryCategoryName,bigCategoryId);
        return SecondaryCategoryDTO.convert(secondaryCategory1);
    }

    @ApiOperation(value = "查询")
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
    public List<SecondaryCategory> selectAll(){
        List<SecondaryCategory> bigCategories = secondaryCategoryService.selectSecondaryCategoryAll();
        return bigCategories;
    }

    @ApiOperation(value = "查询ById")
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    public SecondaryCategory selectSecondaryCategoryById(Integer id){
        SecondaryCategory SecondaryCategory = secondaryCategoryService.selectSecondaryCategoryById(id);

        return  SecondaryCategory;
    }
}
