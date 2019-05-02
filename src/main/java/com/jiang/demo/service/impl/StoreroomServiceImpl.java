package com.jiang.demo.service.impl;

import com.jiang.demo.dto.Storeroom.StoreroomDTO;
import com.jiang.demo.dto.Storeroom.StoreroomForm;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.repository.StoreroomRepository;
import com.jiang.demo.service.StoreroomService;
import com.jiang.demo.utils.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */

@Service
public class StoreroomServiceImpl implements StoreroomService {

    private StoreroomRepository storeroomRepository;

    @Autowired
    public void setStoreroomRepository(StoreroomRepository storeroomRepository) {
        this.storeroomRepository = storeroomRepository;
    }

   /* private GoodsRepository goodsRepository;
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }
*/
    /*更新库房*/
    public List<Storeroom> updateStoreroom(Map<Integer, Integer> map, Date time, String lastPerson) {

        Lock lock = new ReentrantLock();

        List<Storeroom> storeroomList = new ArrayList<>();
        //map  key为goodsId   value为goods Number
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            //根据商品id查询库存  然后改变库存量  操作人  更新时间
            Storeroom storeroom = storeroomRepository.findByGoodsId(entry.getKey());
            //库存量
            Integer amount = storeroom.getAmount();
            //更新时间
            storeroom.setUpdateTime(time);
            //更新库存
            lock.lock();
            storeroom.setAmount(amount + Integer.valueOf(entry.getValue()));
            lock.unlock();
            storeroom.setPerson(lastPerson);

            Storeroom save = storeroomRepository.save(storeroom);
            storeroomList.add(save);

        }

        return storeroomList;
    }

    @Override
    public PageDTO<StoreroomDTO> select(StoreroomForm storeroomForm) {

        Integer pageNum=storeroomForm.getPageNum();
        Integer pageSize=storeroomForm.getPageSize();
        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        //动态分页查出的原始值
        MySpec mySpec = new MySpec(storeroomForm);
        Page<Storeroom> storerooms = storeroomRepository.findAll(mySpec, pageable);

        //封装分页
        PageDTO<StoreroomDTO> pageDTO = new PageDTO<>();
        //将原始封装转换为自定义的分页封装类
        BeanUtils.copyProperties(storerooms, pageDTO);

        List<Storeroom> content = storerooms.getContent();
        List<StoreroomDTO> storeroomDTOS = new ArrayList<>();

        for (Storeroom s : content) {
            storeroomDTOS.add(StoreroomDTO.convert(s));
        }
        pageDTO.setContent(storeroomDTOS);

        return pageDTO;
    }

    private class MySpec implements Specification<Storeroom> {
        private StoreroomForm storeroomForm;

        private MySpec(StoreroomForm storeroomForm) {
            this.storeroomForm = storeroomForm;
        }

        @Override
        public Predicate toPredicate(Root<Storeroom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            //新建商品类  将form转换成goods
            Storeroom storeroom = new Storeroom();
            BeanUtils.copyProperties(storeroomForm, storeroom);


            Integer amount = storeroom.getAmount();
            String person = storeroom.getPerson();
            Date updateTime = storeroom.getUpdateTime();

            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(person)) {
                Predicate predicate = cb.like(root.get("person").as(String.class), "%" + person + "%");
                predicates.add(predicate);
            }
            if (updateTime != null) {
                //不超过
                Predicate predicate = cb.lessThanOrEqualTo(root.get("updateTime").as(Date.class), updateTime);
                predicates.add(predicate);
            }
            if (amount != null) {
                //不超过
                Predicate predicate = cb.lessThanOrEqualTo(root.get("amount").as(Integer.class), amount);
                predicates.add(predicate);
            }
            //商品编号 模糊查询
            if (StringUtils.isNotBlank(storeroomForm.getGoodsCode())){
                //Predicate predicate = cb.like(root.get(storeroom.getGoods().getGoodsCode()),"%"+storeroomForm.getGoodsCode()+"%");
                //ListJoin<Storeroom,Goods> join=root.join(root.getModel().getList("goods",Goods.class));
                Join<Storeroom,Goods> join=root.join("goods");
                Predicate predicate = cb.like(join.get("goodsCode").as(String.class),"%"+storeroomForm.getGoodsCode()+"%");
                System.out.println("开始凭借！！！！！！");
                predicates.add(predicate);
            }
            if(StringUtils.isNotBlank(storeroomForm.getGoodsName())){
                Join<Storeroom,Goods> join=root.join("goods");
                Predicate predicate = cb.like(join.get("goodsName").as(String.class),"%"+storeroomForm.getGoodsName()+"%");
                predicates.add(predicate);
            }


            //判断结合中是否有数据
            if (predicates.size() == 0) {
                return null;
            }
            //将集合转化为CriteriaBuilder所需要的Predicate[]
            Predicate[] predicateArr = new Predicate[predicates.size()];
            predicateArr = predicates.toArray(predicateArr);

            // 返回所有获取的条件： 条件 or 条件 or 条件 or 条件
            return cb.and(predicateArr);
            //return cb.or(predicateArr);

        }

    }
}
