package com.jiang.demo.dto.purchase;


import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.entity.PurchaseDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;


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
    private Boolean isStorage=false;

    @ApiModelProperty(value = "入库时间")
    private Date purchaseTime;

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
}
