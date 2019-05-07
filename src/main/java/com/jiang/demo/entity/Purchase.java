package com.jiang.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   22019/3/25
 */

@Entity
@ApiModel("采购单")
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID",example = "1")
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    private String purchaseCode;

    @ApiModelProperty(value = "订单详情")
    @OneToMany(mappedBy = "purchase",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<PurchaseDetail> purchaseDetails;


    @ApiModelProperty(value = "入库时间")
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//出参时间格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    private Date purchaseTime;

    @ApiModelProperty(value = "操作人员")
    private String person;

    @ApiModelProperty(value = "是否入库")
    private Boolean isStorage=false;

    public Boolean getStorage() {
        return isStorage;
    }

    public void setStorage(Boolean storage) {
        isStorage = storage;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
