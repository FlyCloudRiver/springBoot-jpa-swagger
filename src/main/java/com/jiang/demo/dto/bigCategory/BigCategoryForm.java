package com.jiang.demo.dto.bigCategory;

import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * Author: 江云飞
 * Date:   2019/4/1
 */

@ApiModel("商品大类")
public class BigCategoryForm {

    @ApiModelProperty(value = "商品大类id")
    private  Integer bigCategoryId;

    @ApiModelProperty(value = "商品大类名")
    private  String bigCategoryName;

    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
}
