package com.jiang.demo.dto.bigCategory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Author: 江云飞
 * Date:   2019/4/1
 */

@ApiModel("商品大类")
public class BigCategoryForm {

    @ApiModelProperty(value = "商品大类名")
    private  String bigCategoryName;

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }
}
