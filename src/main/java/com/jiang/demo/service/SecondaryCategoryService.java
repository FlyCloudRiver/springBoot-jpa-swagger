package com.jiang.demo.service;

import com.jiang.demo.entity.SecondaryCategory;

import java.util.Iterator;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */
public interface SecondaryCategoryService {


    SecondaryCategory insertSecondaryCategory(Integer bigCategoryId,String secondaryCategoryName);

    void deleteSecondaryCategoryById(Integer id);

    SecondaryCategory updateSecondaryCategory(Integer id,String secondaryCategoryName,Integer bigCategoryId);

    /*Iterator<SecondaryCategory> selectSecondaryCategoryAll(Integer pageNum,Integer pageSize);*/
    List<SecondaryCategory> selectSecondaryCategoryAll();

    SecondaryCategory selectSecondaryCategoryById(Integer id);
}
