package com.example.cgw.dao;

import com.example.cgw.JPAData.Address;
import com.example.cgw.JPAData.Delivery;
import com.example.cgw.JPAData.Orders;
import com.example.cgw.JPAData.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepo extends JpaRepository<Delivery,Integer> {
    public Delivery findById(int id);
    public Delivery findByUsername(String username);

}

