package com.jiang.demo.service;


import com.jiang.demo.entity.Area;
import com.jiang.demo.entity.City;
import com.jiang.demo.entity.Province;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */
public interface ProvinceService {
    List<Province> select();

    List<City> selectCity(String provinceid);

    List<Area> selectArea(String cityid);
}
