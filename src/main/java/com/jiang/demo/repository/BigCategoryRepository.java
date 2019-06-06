package com.jiang.demo.repository;

import com.jiang.demo.entity.BigCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public interface BigCategoryRepository extends JpaRepository<BigCategory,Integer>  , JpaSpecificationExecutor<BigCategory> {
}
