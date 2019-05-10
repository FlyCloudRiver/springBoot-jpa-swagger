package com.jiang.demo.controller;

import com.jiang.demo.dto.category.CategoryForm;
import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.service.CategoryService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */



/*
* @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
* @PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
 *
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 * 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html
 * */

@RestController
@Api(description = "商品细类" )   //swagger
@RequestMapping("/category")
public class CategoryController {
    // 通过set方法注入  优先选择
    private CategoryService categoryService;
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<CategoryDTO> insertCategory(Integer secondaryCategoryId, String categoryName){
        try{
            return ResultUtil.success(categoryService.insertCategory(secondaryCategoryId, categoryName));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@Permission
    public Result deleteCategory(Integer id){
        try{
            categoryService.deleteCategoryById(id);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }


    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @SuppressWarnings("unchecked")
    public Result<CategoryDTO> updateCategory(Integer id, String categoryName, Integer secondaryCategoryId){
        try{
            return ResultUtil.success(categoryService.updateCategory(id,categoryName,secondaryCategoryId));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }


    }

    @ApiOperation(value = "查询")
    @GetMapping("/selectAll")
    @SuppressWarnings("unchecked")
    public Result<List<CategoryDTO>> selectAll(){

        try{
            return ResultUtil.success(categoryService.selectCategoryAll());
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询ById")
    @PostMapping("/selectOne")
    @SuppressWarnings("unchecked")
    public Result<CategoryDTO> selectCategoryById(Integer id){
        try{
            return ResultUtil.success(categoryService.selectCategoryById(id));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "动态查询")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    public Result<PageDTO<CategoryDTO>> findByDynamicCases(@RequestBody CategoryForm categoryForm){

        try{
            PageDTO<CategoryDTO> goodsPage=categoryService.findByDynamicCases(categoryForm);
            //return goodsPage;
            return ResultUtil.success(goodsPage);
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }
}
