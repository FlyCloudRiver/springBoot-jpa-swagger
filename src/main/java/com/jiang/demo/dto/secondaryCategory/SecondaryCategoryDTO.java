package com.jiang.demo.dto.secondaryCategory;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class SecondaryCategoryDTO {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty(value = "商品中级类名")
    private  String secondaryCategoryName;

    @ApiModelProperty(value = "商品锡、细类")
    private  List<CategoryDTO> categoryDTOS;

    public static SecondaryCategoryDTO convert(SecondaryCategory entity) {
        SecondaryCategoryDTO dto = new SecondaryCategoryDTO();
        BeanUtils.copyProperties(entity, dto);
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        if(entity.getCategoryList()!=null){
            for (Category category : entity.getCategoryList()) {
                categoryDTOS.add(CategoryDTO.convert(category));
            }
        }
        dto.setCategoryDTOS(categoryDTOS);
        return dto;
    }

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }

    public List<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(List<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }



}
