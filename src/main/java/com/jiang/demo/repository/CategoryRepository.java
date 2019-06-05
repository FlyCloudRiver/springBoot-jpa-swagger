package com.jiang.demo.repository;

import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> , JpaSpecificationExecutor<Category> {



    @Query(value = "select * from category where secondary_category_id=?;", nativeQuery = true)
    List<Category> selectCategoryBySecondId(Integer id);

    @Query(value = "select secondary_category_id from category where id=?;", nativeQuery = true)
    Integer selectSecondaryCategoryId(Integer id);


}
