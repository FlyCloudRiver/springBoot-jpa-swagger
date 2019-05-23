package com.jiang.demo.dto.Storeroom;


import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.entity.Storeroom;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class StoreroomDTO {

    private Integer id;

    @ApiModelProperty(value = "库存量")
    private Integer amount;

    @ApiModelProperty(value = "库存商品")
    private GoodsDTO goodsDTO;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人员")
    private String person;

    @ApiModelProperty(value = "出入库类型")
    private String style;

    @ApiModelProperty(value = "出入库数量")
    private Integer number;

    public static StoreroomDTO convert(Storeroom entity) {
        StoreroomDTO dto = new StoreroomDTO();
        BeanUtils.copyProperties(entity, dto);

        if(entity.getGoods()!=null){
            dto.setGoodsDTO(GoodsDTO.convert(entity.getGoods()));
        }

        return dto;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
