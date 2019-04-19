package com.jiang.demo.controller;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.permission.Login;
import com.jiang.demo.permission.Permission;
import com.jiang.demo.service.BigCategoryService;
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


/*
 * @GetMapping("/*")是一个组合注解，是@RequestMapping(value = "", method = RequestMethod.GET)的缩写。
 * @PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。
 *
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
 * 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html
 *
 * @RequestParam将请求中参数的值赋给变量。
 * 也可以不使用@RequestParam，直接接收，此时要求controller方法中的参数名称要跟form中name名称一致
 *
 * @Transactional 事务
 *
 * @Valid 表单验证
 * */


@RestController
@Api(description = "商品大类" )   //swagger
@RequestMapping("/bigCategory")
public class BigCategoryController {

    @Autowired
    private BigCategoryService bigCategoryService;

    /*请求参数名字跟方法中的名字一样   可以省略@RequestParam */
    /*@RequestParam   可以设置默认值defaulValue="0"   也可以要求参数为空 required=false*/
    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    public BigCategoryDTO insertBigCategory(@RequestParam(value = "bigCategoryName") String bigCategoryName){
        BigCategory insertBigCategory=new BigCategory();
        insertBigCategory.setBigCategoryName(bigCategoryName);
        BigCategory entity = bigCategoryService.insertBigCategory(insertBigCategory);
        return BigCategoryDTO.convert(entity);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @Permission
    public void deleteBigCategory(Integer id){
        bigCategoryService.deleteBigCategoryById(id);

    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public BigCategoryDTO updateBigCategory(Integer id,String bigCategoryName){

        BigCategory updateBigCategory=new BigCategory();
        updateBigCategory.setBigCategoryName(bigCategoryName);
        updateBigCategory.setId(id);

        BigCategory bigCategory1 = bigCategoryService.updateBigCategory(updateBigCategory);
        return BigCategoryDTO.convert(bigCategory1);
    }

    @ApiOperation(value = "查询")
    @GetMapping("/selectAll")
    @Login
    public List<BigCategoryDTO> selectAll(){
        List<BigCategory> bigCategories = bigCategoryService.selectBigCategoryAll();

        List<BigCategoryDTO> bigCategoryDTOList=new ArrayList<>();
        for (BigCategory item : bigCategories) {
            bigCategoryDTOList.add(BigCategoryDTO.convert(item));
        }
        return bigCategoryDTOList;
    }

    /*Get方式url传参    地址后面直接  /参数*/
    /* @ApiOperation(value = "查询ById")
    @GetMapping("/selectOne/{id}")
    public Result<BigCategoryDTO> selectBigCategoryById(@PathVariable("id") Integer id){*/
    @ApiOperation(value = "查询ById")
    @GetMapping("/selectOne")
    public Result<BigCategoryDTO> selectBigCategoryById(String userName,Integer id) throws Exception{

        Result<BigCategoryDTO> result=new Result<>();
        BigCategory bigCategory = bigCategoryService.selectBigCategoryById(id);
        BigCategoryDTO convert = BigCategoryDTO.convert(bigCategory);
        return  ResultUtil.success(convert);
       /* Result<BigCategoryDTO> result=new Result<>();
        try{
            BigCategory bigCategory = bigCategoryService.selectBigCategoryById(id);
            BigCategoryDTO convert = BigCategoryDTO.convert(bigCategory);
            return  ResultUtil.success(convert);
        }catch (Exception e){
            System.out.println("错误类型："+e);
            return ResultUtil.error(1,"数据不存在！");
        }*/
    }
}
