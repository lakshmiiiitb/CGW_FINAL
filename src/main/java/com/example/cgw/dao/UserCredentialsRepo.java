package com.example.cgw.dao;

import com.example.cgw.JPAData.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials,Integer> {
    public UserCredentials findByUsername(String username);
    public UserCredentials findById(int id);

}
