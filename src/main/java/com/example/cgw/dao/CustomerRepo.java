package com.example.cgw.dao;

import com.example.cgw.JPAData.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String uname);
    Customer findById(int id);
}
