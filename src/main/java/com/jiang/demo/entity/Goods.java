package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
/*
* 一对多双向关联跟多对一是一样的,在多端生成一个外键,不生成第三张表来管理对应关系,由外键来管理对应关系 */


@Entity
@Table(name = "goods")
//不与@Table结合的话 表名 默认为 SnakeCaseStrategy(命名策略 )为表名
@ApiModel("商品")
public class Goods implements Serializable {

    //商品唯一标识
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "商品ID")
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    @Column(name = "goods_code")
    private  String goodsCode;

    @ApiModelProperty(value = "商品名称")
    @Column(name = "goods_name")
    private  String goodsName;

    @ApiModelProperty(value = "商品售价(元)")
    @Column(name = "goods_price")
    private  Float goodsPrice;

    @ApiModelProperty(value = "商品进价(元)")
    @Column(name = "purchase_price")
    private  Float purchasePrice;

    @ApiModelProperty(value = "商品规格型号")
    @Column(name = "goods_specification")
    private  String goodsSpecification;

    @ApiModelProperty(value = "商品计量单位(桶、包、个、瓶......)")
    @Column(name = "goods_unit")
    private  String goodsUnit;

    @JsonIgnore//防止再去查父类 死循环
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @ApiModelProperty(value = "商品所属厂商")
    private  Supplier supplier;

    @JsonIgnore//防止再去查父类 死循环
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @ApiModelProperty(value = "商品所属类别")
    private  Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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


    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
