package com.jiang.demo.repository;

import com.jiang.demo.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity,Integer> {
}
