package com.jiang.demo.dto.category;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class CategoryDTO {

    @ApiModelProperty(value = "商品ID")
    private Integer id;


    @ApiModelProperty(value = "商品类别")
    private  String categoryName;

    @ApiModelProperty(value = "商品中类")
    private SecondaryCategoryDTO secondaryCategoryDTO;

    public static CategoryDTO convert(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getSecondaryCategory()!=null){
            dto.setSecondaryCategoryDTO(SecondaryCategoryDTO.convert(entity.getSecondaryCategory()));
        }
        return dto;
    }

    public SecondaryCategoryDTO getSecondaryCategoryDTO() {
        return secondaryCategoryDTO;
    }

    public void setSecondaryCategoryDTO(SecondaryCategoryDTO secondaryCategoryDTO) {
        this.secondaryCategoryDTO = secondaryCategoryDTO;
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
}
