package com.example.cgw.dao;

import com.example.cgw.JPAData.Cart;
import com.example.cgw.JPAData.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    List<Cart> findByCustomer(Customer c);
    Cart findById(int id);
    Cart findByItemidAndCustomer(int itemid, Customer customer);
    Cart findByItemid(int id);
}
