package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/3/26
 */

@Entity
@ApiModel("商品类别")
public class Category implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "商品类别ID",example = "1")
    private Integer id;


    @ApiModelProperty(value = "商品类别")
    @Column(name = "category_name")
    private  String categoryName;


    @ApiModelProperty(value = "商品")
    @OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Goods> goodsList;


    @JsonIgnore//防止再去查父类 死循环
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @ApiModelProperty(value = "父级类别")
    private  SecondaryCategory secondaryCategory;

    public SecondaryCategory getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(SecondaryCategory secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }


}
