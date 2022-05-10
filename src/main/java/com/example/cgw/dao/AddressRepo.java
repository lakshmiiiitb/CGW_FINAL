package com.example.cgw.dao;

import com.example.cgw.JPAData.Address;
import com.example.cgw.JPAData.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Integer> {
List<Address>  findByCustomer(Customer c);
@Query("from Address where area=?1 and city=?2 and state=?3 and pincode=?4")
Address findByLocAndCityAndState(String area,String city,String state, String pincode);
List<Address> findByPincode(String pincode);
Address findById(int id);
}
