package com.jiang.demo.dto.purchase;

import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/5/7
 */
public class PurchaseStorageFrom {

    //定单id
    private Integer id;

    //进库操作人员
    private String person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
