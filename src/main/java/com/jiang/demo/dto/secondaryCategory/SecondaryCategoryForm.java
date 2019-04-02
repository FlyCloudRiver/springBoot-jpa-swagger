package com.jiang.demo.dto.secondaryCategory;

import io.swagger.annotations.ApiModelProperty;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class SecondaryCategoryForm {

    @ApiModelProperty(value = "商品中级类名")
    private  String secondaryCategoryName;

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }
}
