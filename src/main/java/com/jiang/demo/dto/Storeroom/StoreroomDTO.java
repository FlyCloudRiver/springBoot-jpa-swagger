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

    private Date updateTime;

    @ApiModelProperty(value = "更新人员")
    private String person;

    public static StoreroomDTO convert(Storeroom entity) {
        StoreroomDTO dto = new StoreroomDTO();
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
