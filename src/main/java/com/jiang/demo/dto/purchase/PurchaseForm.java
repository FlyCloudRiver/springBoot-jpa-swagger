package com.jiang.demo.dto.purchase;


import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import io.swagger.annotations.ApiModelProperty;

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

    @ApiModelProperty(value = "订单生成时间上限(查询)")
    private Date startTime;

    @ApiModelProperty(value = "订单生成时间下限(查询)")
    private Date endTime;

    @ApiModelProperty(value = "是否入库")
    private Boolean isStorage;

    @ApiModelProperty(value = "操作人员")
    private String person;

    private Integer pageNum;
    private Integer pageSize;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getStorage() {
        return isStorage;
    }

    public void setStorage(Boolean storage) {
        isStorage = storage;
    }

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
