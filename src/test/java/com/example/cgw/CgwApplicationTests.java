package com.example.cgw;

import com.example.cgw.dao.CustomerRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class CgwApplicationTests {

    @Autowired
    CustomerRepo customerRepo;
    @Test
    void contextLoads() {

    }
}
