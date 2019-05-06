package com.jiang.demo.repository;


import com.jiang.demo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByProvinceid(String provinceid);
}
