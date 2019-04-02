package com.jiang.demo.dto.Supplier;

import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.Supplier;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class SupplierDTO {

    private Integer id;

    @ApiModelProperty(value = "供应商商号")
    private  String supplierCode;

    @ApiModelProperty(value = "供应商名")
    private  String supplierName;

    @ApiModelProperty(value = "供应商电话")
    private  String supplierPhone;

    @ApiModelProperty(value = "供应商官网")
    private  String supplierWeb;

    @ApiModelProperty(value = "供应商地址")
    private  String supplierAddress;

    public static SupplierDTO convert(Supplier entity) {
        SupplierDTO dto = new SupplierDTO();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

}
