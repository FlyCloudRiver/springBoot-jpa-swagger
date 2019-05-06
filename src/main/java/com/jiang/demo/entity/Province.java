package com.jiang.demo.entity;

import javax.persistence.*;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */

@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;//主键.

    @Column(name = "provinceid",columnDefinition="varchar(11)",nullable = false)
    private String provinceid;

    @Column(name = "province",columnDefinition="varchar(100)",nullable = false)
    private String province;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
