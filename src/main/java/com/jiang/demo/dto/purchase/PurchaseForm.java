package com.jiang.demo.dto.purchase;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class PurchaseForm {

    @ApiModelProperty(value = "订单编号")
    private String purchaseCode;

    @ApiModelProperty(value = "订单详情")
    private List<PurchaseDetailForm> purchaseDetailForms;

    @ApiModelProperty(value = "更新时间")
    @Temporal(TemporalType.DATE)//生成yyyy-MM-dd类型的日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//出参时间格式化
    @DateTimeFormat(pattern = "yyyy-MM-dd")//入参格式化
    private Date purchaseTime;

    @ApiModelProperty(value = "操作人员")
    private String person;

    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }


    public List<PurchaseDetailForm> getPurchaseDetailForms() {
        return purchaseDetailForms;
    }

    public void setPurchaseDetailForms(List<PurchaseDetailForm> purchaseDetailForms) {
        this.purchaseDetailForms = purchaseDetailForms;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

}
