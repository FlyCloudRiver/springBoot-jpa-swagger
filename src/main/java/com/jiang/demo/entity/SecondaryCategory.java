package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/3/28
 */


@Entity
@ApiModel("商品中级类别")
public class SecondaryCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "商品中级类别ID", example = "1")
    private Integer id;


    @ApiModelProperty(value = "商品中级类名")
    @Column(name = "secondary_category_name")
    private String secondaryCategoryName;

    /*子级分类*/
    @ApiModelProperty(value = "分类")
    @OneToMany(mappedBy = "secondaryCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categoryList;


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @ApiModelProperty(value = "父级类别")
    private BigCategory bigCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public BigCategory getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(BigCategory bigCategory) {
        this.bigCategory = bigCategory;
    }

}
