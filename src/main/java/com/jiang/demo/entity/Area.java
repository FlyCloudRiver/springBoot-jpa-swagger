package com.jiang.demo.entity;

import javax.persistence.*;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */

@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;//主键.

    @Column(name = "areaid",columnDefinition="char(6) COMMENT '区县编码'",nullable = false)
    private String areaid;

    @Column(name = "area",columnDefinition="varchar(40) COMMENT '区县名称'",nullable = false)
    private String area;

    @Column(name = "cityid",columnDefinition="char(6) COMMENT '所属城市编码'",nullable = false)
    private String cityid;

   /* @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,}, optional = false)
    @JsonIgnore
    private City city;*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
}
