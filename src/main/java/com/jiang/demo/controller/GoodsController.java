package com.jiang.demo.controller;



import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "商品" )   //swagger
@RequestMapping("/goods")
public class GoodsController {


}
