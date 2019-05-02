package com.jiang.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/3/28
 */

@Entity
@ApiModel("商品大类")
@Component
public class BigCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @ApiModelProperty(value = "商品大类ID",example = "1")
    private Integer id;


    @ApiModelProperty(value = "商品大类名")
    @Column(name = "big_category_name")//可省
    private  String bigCategoryName;

    /*子级分类*/
    @ApiModelProperty(value = "子分类")
    @OneToMany(mappedBy = "bigCategory",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<SecondaryCategory> secondaryCategoryList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

    public List<SecondaryCategory> getSecondaryCategoryList() {
        return secondaryCategoryList;
    }

    public void setSecondaryCategoryList(List<SecondaryCategory> secondaryCategoryList) {
        this.secondaryCategoryList = secondaryCategoryList;
    }


}
