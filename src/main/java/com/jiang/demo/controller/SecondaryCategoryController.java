package com.jiang.demo.controller;
import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.permission.Permission;
import com.jiang.demo.service.SecondaryCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @PostMapping("/insert")
    //@RequestMapping(value = "/insert", method = RequestMethod.POST)
    public SecondaryCategoryDTO insertSecondaryCategory(Integer bigCategoryId,String secondaryCategoryName){

        SecondaryCategory entity = secondaryCategoryService.insertSecondaryCategory(bigCategoryId,secondaryCategoryName);
        return SecondaryCategoryDTO.convert(entity);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @Permission
    public void deleteSecondaryCategory(Integer id){
        secondaryCategoryService.deleteSecondaryCategoryById(id);

    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public SecondaryCategoryDTO updateSecondaryCategory(Integer id,String secondaryCategoryName,Integer bigCategoryId){
        SecondaryCategory secondaryCategory1 = secondaryCategoryService.updateSecondaryCategory(id,secondaryCategoryName,bigCategoryId);
        return SecondaryCategoryDTO.convert(secondaryCategory1);
    }

    @ApiOperation(value = "查询")
    @PostMapping("/selectAll")
    public List<SecondaryCategoryDTO> selectAll(){
        List<SecondaryCategory> secondaryCategoryList = secondaryCategoryService.selectSecondaryCategoryAll();
       List<SecondaryCategoryDTO> secondaryCategoryDTOList = new ArrayList<>();
        for (SecondaryCategory item:secondaryCategoryList ) {
            secondaryCategoryDTOList.add(SecondaryCategoryDTO.convert(item));
        }
        return secondaryCategoryDTOList;
    }

    @ApiOperation(value = "查询ById")
    @GetMapping("/selectOne")
    public SecondaryCategoryDTO selectSecondaryCategoryById(Integer id){
        SecondaryCategory SecondaryCategory = secondaryCategoryService.selectSecondaryCategoryById(id);

        return  SecondaryCategoryDTO.convert(SecondaryCategory);
    }
}
