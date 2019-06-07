package com.jiang.demo.controller;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.bigCategory.BigCategoryForm;
import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.dto.newCategory.NewCategoryForm;
import com.jiang.demo.permission.Login;
import com.jiang.demo.service.CategoryNewService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: 江云飞
 * Date:   2019/6/3
 */

@RestController
@Api(description = "种类和细类同时增加和更新" )
@RequestMapping("/allCategory")
public class CategoryNewController {

    private CategoryNewService categoryNewService;
    @Autowired
    public void setCategoryNewService(CategoryNewService categoryNewService) {
        this.categoryNewService = categoryNewService;
    }

    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    @Login
    public Result<BigCategoryDTO> insertAllCategory(@RequestBody NewCategoryForm newCategoryForm){
        try{
            return ResultUtil.success(categoryNewService.insertAllCategory(newCategoryForm));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    @SuppressWarnings("unchecked")
    @Login
    public Result updateAllCategory(@RequestBody BigCategoryDTO bigCategoryDTO){
        try{
            categoryNewService.updateAllCategory(bigCategoryDTO);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

}
