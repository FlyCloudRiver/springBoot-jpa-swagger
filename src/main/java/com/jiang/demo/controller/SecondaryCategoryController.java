package com.jiang.demo.controller;
import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.service.SecondaryCategoryService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
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
    // 通过set方法注入  优先选择
    private SecondaryCategoryService secondaryCategoryService;
    @Autowired
    public void setSecondaryCategoryService(SecondaryCategoryService secondaryCategoryService) {
        this.secondaryCategoryService = secondaryCategoryService;
    }

    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    //@RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result<SecondaryCategoryDTO> insertSecondaryCategory(Integer bigCategoryId, String secondaryCategoryName){
        try{
            SecondaryCategory entity = secondaryCategoryService.insertSecondaryCategory(bigCategoryId,secondaryCategoryName);
            //return SecondaryCategoryDTO.convert(entity);
            return ResultUtil.success(SecondaryCategoryDTO.convert(entity));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
   // @Permission
    public Result deleteSecondaryCategory(Integer id){
        try{
            secondaryCategoryService.deleteSecondaryCategoryById(id);
            return ResultUtil.success();
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @SuppressWarnings("unchecked")
    public Result<SecondaryCategoryDTO> updateSecondaryCategory(Integer id,String secondaryCategoryName,Integer bigCategoryId){
        try{
            SecondaryCategory secondaryCategory1 = secondaryCategoryService.updateSecondaryCategory(id,secondaryCategoryName,bigCategoryId);
            //return SecondaryCategoryDTO.convert(secondaryCategory1);
            return ResultUtil.success(SecondaryCategoryDTO.convert(secondaryCategory1));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询")
    @PostMapping("/selectAll")
    @SuppressWarnings("unchecked")
    public Result<List<SecondaryCategoryDTO>> selectAll(){
        try{
            List<SecondaryCategory> secondaryCategoryList = secondaryCategoryService.selectSecondaryCategoryAll();
            List<SecondaryCategoryDTO> secondaryCategoryDTOList = new ArrayList<>();
            for (SecondaryCategory item:secondaryCategoryList ) {
                secondaryCategoryDTOList.add(SecondaryCategoryDTO.convert(item));
            }
            return ResultUtil.success(secondaryCategoryDTOList);
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }

    @ApiOperation(value = "查询ById")
    @GetMapping("/selectOne")
    @SuppressWarnings("unchecked")
    public Result<SecondaryCategoryDTO> selectSecondaryCategoryById(Integer id){

        try{
            SecondaryCategory SecondaryCategory = secondaryCategoryService.selectSecondaryCategoryById(id);

            //return  SecondaryCategoryDTO.convert(SecondaryCategory);
            return ResultUtil.success(SecondaryCategoryDTO.convert(SecondaryCategory));
        }catch (Exception e){
            return ResultUtil.error(-1,e.getMessage());
        }

    }
}
