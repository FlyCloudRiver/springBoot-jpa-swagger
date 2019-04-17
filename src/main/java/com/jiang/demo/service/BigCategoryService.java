package com.jiang.demo.service;

import com.jiang.demo.entity.BigCategory;


import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */


public interface BigCategoryService {

    BigCategory insertBigCategory(BigCategory bigCategory);

    void deleteBigCategoryById(Integer id);

    BigCategory updateBigCategory(BigCategory bigCategory);

    List<BigCategory> selectBigCategoryAll();

    BigCategory selectBigCategoryById(Integer id) throws Exception;
}
