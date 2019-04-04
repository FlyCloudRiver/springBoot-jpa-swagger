package com.jiang.demo.controller;

import com.jiang.demo.dto.category.CategoryDTO;

import com.jiang.demo.entity.Category;
import com.jiang.demo.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */

@RestController
@Api(description = "商品细类" )   //swagger
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "添加")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public CategoryDTO insertCategory(Integer econdaryCategoryId, String categoryName){
        Category category = categoryService.insertCategory(econdaryCategoryId, categoryName);
        return CategoryDTO.convert(category);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteCategory(Integer id){
        categoryService.deleteCategoryById(id);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CategoryDTO updateCategory(Integer id, String categoryName, Integer secondaryCategoryId){

        Category category = categoryService.updateCategory(id,categoryName,secondaryCategoryId);
        return CategoryDTO.convert(category);
    }

    @ApiOperation(value = "查询")
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public List<CategoryDTO> selectAll(){
        List<Category> categories = categoryService.selectCategoryAll();

        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        for (Category item:categories) {
            categoryDTOList.add(CategoryDTO.convert(item));
        }
        return categoryDTOList;
    }

    @ApiOperation(value = "查询ById")
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    public CategoryDTO selectCategoryById(Integer id){
        Category category = categoryService.selectCategoryById(id);

        return  CategoryDTO.convert(category);
    }
}
