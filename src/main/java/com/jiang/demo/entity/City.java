package com.jiang.demo.entity;

import javax.persistence.*;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;//主键.

    @Column(name = "cityid",columnDefinition="char(6) COMMENT '城市编码'",nullable = false)
    private String cityid;

    @Column(name = "city",columnDefinition="varchar(40) COMMENT '城市名称'",nullable = false)
    private String city;

    @Column(name = "provinceid",columnDefinition="char(6) COMMENT '所属省份编码'",nullable = false)
    private String provinceid;
/*
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,}, optional = false)
    @JsonIgnore
    private Province province;

    @OneToMany(mappedBy = "city",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Area> areas;*/



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }
}
