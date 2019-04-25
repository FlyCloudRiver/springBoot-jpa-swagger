package com.jiang.demo.dto.purchaseDetail;


import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.entity.PurchaseDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;


/**
 * Author: 江云飞
 * Date:   2019/4/22
 */
public class PurchaseDetailDTO {

    private Integer id;

    @ApiModelProperty(value = "商品")
    private GoodsDTO goodsDTO;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;


    public static PurchaseDetailDTO convert(PurchaseDetail entity) {
        PurchaseDetailDTO dto = new PurchaseDetailDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getGoods()!=null){
            dto.setGoodsDTO(GoodsDTO.convert(entity.getGoods()));
        }
        return dto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
}
