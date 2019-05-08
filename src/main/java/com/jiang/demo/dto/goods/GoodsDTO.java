package com.jiang.demo.dto.goods;

import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.entity.Goods;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class GoodsDTO {
    @ApiModelProperty(value = "商品ID")
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    private  String goodsCode;

    @ApiModelProperty(value = "商品名称")
    private  String goodsName;

    @ApiModelProperty(value = "商品单价")
    private  Float goodsPrice;

    @ApiModelProperty(value = "商品保质期")
    private  String goodsShelfLife;

    @ApiModelProperty(value = "商品生产日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsDate;

    @ApiModelProperty(value = "商品所属厂商id")
    private Integer supplierId;

    @ApiModelProperty(value = "商品所属类别DTO")
    private Integer categoryId;
    public static GoodsDTO convert(Goods entity) {
        GoodsDTO dto = new GoodsDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getCategory()!=null){
            dto.setCategoryId(entity.getCategory().getId());
        }
        if(entity.getSupplier()!=null){
            dto.setSupplierId(entity.getSupplier().getId());

        }
        return dto;
    }
    /*@ApiModelProperty(value = "商品所属厂商DTO")
    private SupplierDTO supplierDTO;

    @ApiModelProperty(value = "商品所属类别DTO")
    private CategoryDTO categoryDTO;*/

   /* public static GoodsDTO convert(Goods entity) {
        GoodsDTO dto = new GoodsDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getCategory()!=null){
            dto.setCategoryDTO(CategoryDTO.convert(entity.getCategory()));
        }
        if(entity.getSupplier()!=null){
            dto.setSupplierDTO(SupplierDTO.convert(entity.getSupplier()));

        }
        return dto;
    }*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsShelfLife() {
        return goodsShelfLife;
    }

    public void setGoodsShelfLife(String goodsShelfLife) {
        this.goodsShelfLife = goodsShelfLife;
    }

    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
