package com.jiang.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * Author: 江云飞
 * Date:   2019/4/16
 */
//单元测试   打包的时候可以跳过
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BigCategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void insertBigCategory() {
    }

    @Test
    public void deleteBigCategory() {
    }

    @Test
    public void selectAll() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/bigCategory/selectAll"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
