package com.jiang.demo.repository;

import com.jiang.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
