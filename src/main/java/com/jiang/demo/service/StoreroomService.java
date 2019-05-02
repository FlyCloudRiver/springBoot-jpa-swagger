package com.jiang.demo.service;


import com.jiang.demo.dto.Storeroom.StoreroomDTO;
import com.jiang.demo.dto.Storeroom.StoreroomForm;
import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.utils.PageDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomService {

    List<Storeroom> updateStoreroom(Map<Integer,Integer> map, Date time, String lastPerson);

    PageDTO<StoreroomDTO> select(StoreroomForm storeroomForm);
}
