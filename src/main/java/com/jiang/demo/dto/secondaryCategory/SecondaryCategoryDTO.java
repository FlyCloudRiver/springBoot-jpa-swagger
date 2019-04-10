package com.jiang.demo.dto.secondaryCategory;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;


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

    @ApiModelProperty(value = "商品大类")
    private BigCategoryDTO bigCategoryDTO;

    public static SecondaryCategoryDTO convert(SecondaryCategory entity) {
        SecondaryCategoryDTO dto = new SecondaryCategoryDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getBigCategory()!=null){
            dto.setBigCategoryDTO(BigCategoryDTO.convert(entity.getBigCategory()));
        }
        return dto;
    }

    public BigCategoryDTO getBigCategoryDTO() {
        return bigCategoryDTO;
    }

    public void setBigCategoryDTO(BigCategoryDTO bigCategoryDTO) {
        this.bigCategoryDTO = bigCategoryDTO;
    }

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


}