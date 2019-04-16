package com.jiang.demo.controller;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.category.CategoryDTO;

import com.jiang.demo.entity.Category;
import com.jiang.demo.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    public CategoryDTO insertCategory(Integer secondaryCategoryId, String categoryName){
        Category category = categoryService.insertCategory(secondaryCategoryId, categoryName);
        return CategoryDTO.convert(category);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public void deleteCategory(Integer id){
        categoryService.deleteCategoryById(id);
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public CategoryDTO updateCategory(Integer id, String categoryName, Integer secondaryCategoryId){

        Category category = categoryService.updateCategory(id,categoryName,secondaryCategoryId);
        return CategoryDTO.convert(category);
    }

    @ApiOperation(value = "查询")
    @GetMapping("/selectAll")
    public List<CategoryDTO> selectAll(){
        List<Category> categories = categoryService.selectCategoryAll();

        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        for (Category item:categories) {
            categoryDTOList.add(CategoryDTO.convert(item));
        }
        return categoryDTOList;
    }

    @ApiOperation(value = "查询ById")
    @GetMapping("/selectOne")
    public CategoryDTO selectCategoryById(Integer id){
        Category category = categoryService.selectCategoryById(id);

        return  CategoryDTO.convert(category);
    }

    @ApiOperation(value = "动态查询")
    @PostMapping("/select")
    public PageDTO<CategoryDTO> findByDynamicCases(Integer bigCategoryId,Integer secondaryCategoryId, Integer pageNum, Integer pageSize){

        PageDTO<CategoryDTO> goodsPage=categoryService.findByDynamicCases(bigCategoryId,secondaryCategoryId,pageNum, pageSize);
        System.out.println(goodsPage);

        return goodsPage;
    }
}
