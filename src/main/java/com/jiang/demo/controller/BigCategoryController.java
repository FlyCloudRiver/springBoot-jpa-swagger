package com.jiang.demo.controller;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.bigCategory.BigCategoryForm;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.User;
import com.jiang.demo.service.BigCategoryService;
import com.jiang.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */

@RestController
@Api(description = "商品大类" )   //swagger
@RequestMapping("/bigCategory")
public class BigCategoryController {

    @Autowired
    private BigCategoryService bigCategoryService;

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BigCategoryDTO insertBigCategory(String bigCategoryName){
        BigCategory insertbigCategory=new BigCategory();
        insertbigCategory.setBigCategoryName(bigCategoryName);
        BigCategory entity = bigCategoryService.insertBigCategory(insertbigCategory);
        return BigCategoryDTO.convert(entity);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteBigCategory(Integer id){
        bigCategoryService.deleteBigCategoryById(id);

    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BigCategoryDTO updateBigCategory(Integer id,String bigCategoryName){

        BigCategory updatebigCategory=new BigCategory();
        updatebigCategory.setBigCategoryName(bigCategoryName);
        updatebigCategory.setId(id);

        BigCategory bigCategory1 = bigCategoryService.updateBigCategory(updatebigCategory);
        return BigCategoryDTO.convert(bigCategory1);
    }

    @ApiOperation(value = "查询")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public List<BigCategory> selectAll(){
        List<BigCategory> bigCategories = bigCategoryService.selectBigCategoryAll();
        return bigCategories;
    }

    @ApiOperation(value = "查询ById")
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    public BigCategory selectBigcategoryById(Integer id){
        BigCategory bigCategory = bigCategoryService.selectBigCategoryById(id);

        return  bigCategory;
    }
}
