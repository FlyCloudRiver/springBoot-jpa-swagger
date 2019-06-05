package com.jiang.demo.dto.newCategory;

import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryForm;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/6/5
 */
public class NewCategoryForm {
    @ApiModelProperty(value = "大类ID")
    private  Integer bigId;

    @ApiModelProperty(value = "商品中类")
    private List<SecondaryCategoryForm> secondaryCategoryForms;

    public Integer getBigId() {
        return bigId;
    }

    public void setBigId(Integer bigId) {
        this.bigId = bigId;
    }

    public List<SecondaryCategoryForm> getSecondaryCategoryForms() {
        return secondaryCategoryForms;
    }

    public void setSecondaryCategoryForms(List<SecondaryCategoryForm> secondaryCategoryForms) {
        this.secondaryCategoryForms = secondaryCategoryForms;
    }
}
