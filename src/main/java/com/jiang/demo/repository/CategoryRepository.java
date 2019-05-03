package com.jiang.demo.repository;

import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> , JpaSpecificationExecutor<Category> {
}
