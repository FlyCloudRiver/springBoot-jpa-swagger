package com.jiang.demo.dto.goods;


import com.jiang.demo.entity.Goods;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;


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

    @ApiModelProperty(value = "商品售价(元)")
    private  Float goodsPrice;

    @ApiModelProperty(value = "商品进价(元)")
    private  Float purchasePrice;

    @ApiModelProperty(value = "商品规格型号")
    private  String goodsSpecification;

    @ApiModelProperty(value = "商品计量单位(桶、包、个、瓶......)")
    private  String goodsUnit;

    @ApiModelProperty(value = "商品所属厂商id")
    private Integer supplierId;


    @ApiModelProperty(value = "商品所属厂商Name")
    private String supplierName;

    @ApiModelProperty(value = "商品所属大类id")
    private Integer bigCategoryId;

    @ApiModelProperty(value = "商品所属大类name")
    private String bigCategoryName;

    @ApiModelProperty(value = "商品所属中类id")
    private Integer secondaryCategoryId;

    @ApiModelProperty(value = "商品所属中类name")
    private String secondaryCategoryName;

    @ApiModelProperty(value = "商品所属细类id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品所属细类name")
    private String categoryName;

    public static GoodsDTO convert(Goods entity) {
        GoodsDTO dto = new GoodsDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getCategory()!=null){
            dto.setCategoryId(entity.getCategory().getId());
            dto.setCategoryName(entity.getCategory().getCategoryName());
        }
        if(entity.getSupplier()!=null){
            dto.setSupplierId(entity.getSupplier().getId());
            dto.setSupplierName(entity.getSupplier().getSupplierName());

        }
        return dto;
    }

    public Integer getBigCategoryId() {
        return bigCategoryId;
    }

    public void setBigCategoryId(Integer bigCategoryId) {
        this.bigCategoryId = bigCategoryId;
    }

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

    public Integer getSecondaryCategoryId() {
        return secondaryCategoryId;
    }

    public void setSecondaryCategoryId(Integer secondaryCategoryId) {
        this.secondaryCategoryId = secondaryCategoryId;
    }

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }

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


    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
}
