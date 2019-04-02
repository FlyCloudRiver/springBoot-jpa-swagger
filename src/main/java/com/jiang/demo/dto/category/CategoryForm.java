package com.jiang.demo.dto.category;

import io.swagger.annotations.ApiModelProperty;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class CategoryForm {

    @ApiModelProperty(value = "商品类别")
    private  String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
