package com.jiang.demo.repository;


import com.jiang.demo.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/5/6
 */
public interface AreaRepository extends JpaRepository<Area, Integer> {

    List<Area> findByCityid(String cityid);
}
