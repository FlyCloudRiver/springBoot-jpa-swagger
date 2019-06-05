package com.jiang.demo.dto.secondaryCategory;

import com.jiang.demo.dto.category.CategoryForm;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class SecondaryCategoryForm {


    @ApiModelProperty(value = "商品中级类名")
    private  String secondaryCategoryName;

    @ApiModelProperty(value = "商品细类")
    private List<CategoryForm> categoryForms;

    public List<CategoryForm> getCategoryForms() {
        return categoryForms;
    }

    public void setCategoryForms(List<CategoryForm> categoryForms) {
        this.categoryForms = categoryForms;
    }



    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }
}
