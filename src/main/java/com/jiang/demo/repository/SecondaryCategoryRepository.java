package com.jiang.demo.repository;

import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public interface SecondaryCategoryRepository extends JpaRepository<SecondaryCategory,Integer> {


    @Query(value = "select * from secondary_category where big_category_id=?;", nativeQuery = true)
    List<SecondaryCategory> selectCategoryByBigId(Integer bigCategoryId);

    @Query(value = "select big_category_id from secondary_category where id=?;", nativeQuery = true)
    Integer selectBigCategoryId(Integer id);
}
