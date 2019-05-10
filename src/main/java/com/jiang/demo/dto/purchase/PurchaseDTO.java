package com.jiang.demo.dto.purchase;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.entity.PurchaseDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class PurchaseDTO {

    private Integer id;

    @ApiModelProperty(value = "订单编号")
    private String purchaseCode;

    @ApiModelProperty(value = "是否入库")
    private Boolean isStorage;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "入库时间")
    private Date storeTime;

    @ApiModelProperty(value = "订单更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "操作人员")
    private String person;
    @ApiModelProperty(value = "订单详情")
    private List<PurchaseDetailDTO> purchaseDetailDTOS;


    public static PurchaseDTO convert(Purchase entity) {
        PurchaseDTO dto = new PurchaseDTO();
        BeanUtils.copyProperties(entity, dto);

        //订单详情DTO
        List<PurchaseDetailDTO> purchaseDetails=new ArrayList<>();

        if(entity.getPurchaseDetails()!=null){
            //便利订单详情
            for (PurchaseDetail g:entity.getPurchaseDetails()) {

                purchaseDetails.add(PurchaseDetailDTO.convert(g));
            }
            dto.setPurchaseDetailDTOS(purchaseDetails);
        }
        return dto;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStorage() {
        return isStorage;
    }

    public void setStorage(Boolean storage) {
        isStorage = storage;
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

    public List<PurchaseDetailDTO> getPurchaseDetailDTOS() {
        return purchaseDetailDTOS;
    }

    public void setPurchaseDetailDTOS(List<PurchaseDetailDTO> purchaseDetailDTOS) {
        this.purchaseDetailDTOS = purchaseDetailDTOS;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
