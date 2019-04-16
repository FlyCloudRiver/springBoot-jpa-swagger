package com.jiang.demo.controller;

import com.jiang.demo.config.PageDTO;
import com.jiang.demo.dto.category.CategoryDTO;

import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
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



/**
* @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
* @PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
 * */

@RestController//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
//如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html
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

    @ApiOperation(value = "动态查询")
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public PageDTO<CategoryDTO> findByDynamicCases(Integer bigCategoryId,Integer secondaryCategoryId, Integer pageNum, Integer pageSize){

        PageDTO<CategoryDTO> goodsPage=categoryService.findByDynamicCases(bigCategoryId,secondaryCategoryId,pageNum, pageSize);
        System.out.println(goodsPage);

        return goodsPage;
    }
}
