package com.jiang.demo.dto.bigCategory;


import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.SecondaryCategory;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public class BigCategoryDTO {

    @ApiModelProperty("商品大类ID")
    private Integer id;

    @ApiModelProperty(value = "商品大类名")
    private  String bigCategoryName;

    @ApiModelProperty(value = "商品中类")
    private List<SecondaryCategoryDTO> secondaryCategoryDTOS;


    public static BigCategoryDTO convert(BigCategory entity) {
        BigCategoryDTO dto = new BigCategoryDTO();
        BeanUtils.copyProperties(entity, dto);
        List<SecondaryCategoryDTO> secondaryCategoryDTOS = new ArrayList<>();
        if(entity.getSecondaryCategoryList()!=null){
            for (SecondaryCategory secondaryCategory : entity.getSecondaryCategoryList()) {
                secondaryCategoryDTOS.add(SecondaryCategoryDTO.convert(secondaryCategory));
            }
        }
        dto.setSecondaryCategoryDTOS(secondaryCategoryDTOS);
        return dto;
    }

    public List<SecondaryCategoryDTO> getSecondaryCategoryDTOS() {
        return secondaryCategoryDTOS;
    }

    public void setSecondaryCategoryDTOS(List<SecondaryCategoryDTO> secondaryCategoryDTOS) {
        this.secondaryCategoryDTOS = secondaryCategoryDTOS;
    }

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

}
