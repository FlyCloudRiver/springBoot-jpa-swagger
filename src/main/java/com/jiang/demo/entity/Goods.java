package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


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

    @ApiModelProperty(value = "商品单价")
    @Column(name = "goods_price")
    private  Float goodsPrice;

    @ApiModelProperty(value = "商品数量")
    @Column(name = "goods_number")
    private  Integer goodsNumber;

    @ApiModelProperty(value = "商品保质期")
    @Column(name = "goods_shelf_life")
    private  Integer goodsShelfLife;

    @ApiModelProperty(value = "商品生产日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "goods_date")
    private Date goodsDate;

    @JsonIgnore//防止再去查父类 死循环
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @ApiModelProperty(value = "商品所属厂商")
    private  Supplier supplier;

    /*@JsonIgnore//防止再去查父类 死循环*/
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

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsShelfLife() {
        return goodsShelfLife;
    }

    public void setGoodsShelfLife(Integer goodsShelfLife) {
        this.goodsShelfLife = goodsShelfLife;
    }

    public Date getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(Date goodsDate) {
        this.goodsDate = goodsDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


}
