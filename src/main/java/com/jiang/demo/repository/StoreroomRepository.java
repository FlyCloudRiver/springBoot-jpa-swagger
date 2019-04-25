package com.jiang.demo.repository;

import com.jiang.demo.entity.Storeroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomRepository extends JpaRepository<Storeroom,Integer>, JpaSpecificationExecutor<Storeroom> {
}
