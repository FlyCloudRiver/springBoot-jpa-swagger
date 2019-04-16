package com.jiang.demo.repository;

import com.jiang.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> ,JpaSpecificationExecutor<Supplier> {
}
