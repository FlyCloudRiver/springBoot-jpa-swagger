package com.jiang.demo.dto.shipmentDetail;

import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.entity.ShipmentDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * Author: 江云飞
 * Date:   2019/4/25
 */
public class ShipmentDetailDTO {

    private Integer id;

    @ApiModelProperty(value = "商品")
    private GoodsDTO goodsDTO;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "总库存")
    private Integer goodsAmount;

    public static ShipmentDetailDTO convert(ShipmentDetail entity) {
        ShipmentDetailDTO dto = new ShipmentDetailDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getGoods()!=null){
            dto.setGoodsDTO(GoodsDTO.convert(entity.getGoods()));
        }
        return dto;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
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
