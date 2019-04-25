package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */

@Entity
@ApiModel("库房")
public class Storeroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID",example = "1")
    private Integer id;

    @ApiModelProperty(value = "库存量")
    @Column(nullable=false,name="amount",columnDefinition="int default 0")
    private Integer amount;

    @ApiModelProperty(value = "库存商品")
    @OneToOne
    private Goods goods;

    @ApiModelProperty(value = "更新时间")
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//出参时间格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    private Date updateTime;

    @ApiModelProperty(value = "更新人员")
    private String person;

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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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
