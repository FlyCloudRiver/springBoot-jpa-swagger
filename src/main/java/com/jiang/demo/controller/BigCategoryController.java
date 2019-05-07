package com.jiang.demo.controller;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.permission.Permission;
import com.jiang.demo.service.BigCategoryService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //通过变量（field）注入  不建议选择
   /* @Autowired
    private BigCategoryService bigCategoryService;*/

    /*构造方法注入*/
    /*private BigCategoryService bigCategoryService;
    @Autowired
    public BigCategoryController (BigCategoryService bigCategoryService) {
        this.bigCategoryService=bigCategoryService;
    }*/
    /*构造方法注入*/

    // 通过set方法注入  优先选择
    private BigCategoryService bigCategoryService;
    @Autowired
    public void setBigCategoryService(BigCategoryService bigCategoryService) {
        this.bigCategoryService = bigCategoryService;
    }

    /*请求参数名字跟方法中的名字一样   可以省略@RequestParam */
    /*@RequestParam   可以设置默认值defaulValue="0"   也可以要求参数为空 required=false*/
    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
    public Result<BigCategoryDTO> insertBigCategory(String bigCategoryName){
        BigCategory insertBigCategory=new BigCategory();
        insertBigCategory.setBigCategoryName(bigCategoryName);
        //return BigCategoryDTO.convert(entity);
        return ResultUtil.success(bigCategoryService.insertBigCategory(insertBigCategory));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@Permission
    public Result deleteBigCategory(Integer id){
        bigCategoryService.deleteBigCategoryById(id);
        return ResultUtil.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    //@Login
    //@Permission
    @SuppressWarnings("unchecked")//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
    public Result<BigCategoryDTO> updateBigCategory( Integer id, String bigCategoryName){

        return ResultUtil.success(bigCategoryService.updateBigCategory(id,bigCategoryName));
    }

    @ApiOperation(value = "查询")
    @GetMapping("/selectAll")
    @SuppressWarnings("unchecked")//告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。

    public Result<List<BigCategoryDTO>> selectAll(){
        return ResultUtil.success(bigCategoryService.selectBigCategoryAll());
    }

    /*Get方式url传参    地址后面直接  /参数*/
    /* @ApiOperation(value = "查询ById")
    @GetMapping("/selectOne/{id}")
    public Result<BigCategoryDTO> selectBigCategoryById(@PathVariable("id") Integer id){*/
    @ApiOperation(value = "查询ById")
    @PostMapping("/selectOne")
    @SuppressWarnings("unchecked")
    public Result<BigCategoryDTO> selectBigCategoryById(@RequestBody Integer id) throws Exception{

        return  ResultUtil.success(bigCategoryService.selectBigCategoryById(id));

    }
}
