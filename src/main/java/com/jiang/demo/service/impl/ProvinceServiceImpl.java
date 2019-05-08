package com.jiang.demo.service.impl;


import com.jiang.demo.entity.Area;
import com.jiang.demo.entity.City;
import com.jiang.demo.entity.Province;
import com.jiang.demo.repository.AreaRepository;
import com.jiang.demo.repository.CityRepository;
import com.jiang.demo.repository.ProvinceRepository;
import com.jiang.demo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceRepository provinceRepository;
    @Autowired
    public void setProvinceRepository(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    private CityRepository cityRepository;
    @Autowired
    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    private AreaRepository areaRepository;
    @Autowired
    public void setAreaRepository(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Province> select(){
        return provinceRepository.findAll();
    }

    @Override
    public List<City> selectCity(String provinceid) {

        return cityRepository.findByProvinceid(provinceid);
    }

    @Override
    public List<Area> selectArea(String cityid) {
        return areaRepository.findByCityid(cityid);
    }
}
