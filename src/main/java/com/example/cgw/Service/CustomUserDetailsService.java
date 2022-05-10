package com.example.cgw.Service;

import com.example.cgw.JPAData.UserCredentials;
import com.example.cgw.controller.Pages;
import com.example.cgw.dao.UserCredentialsRepo;
import com.example.cgw.JPAData.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserCredentialsRepo userCredentialsRepo;
    //fetch user from database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserCredentials user= userCredentialsRepo.findByUsername(username);
        System.out.println("inside custome user details service loadbyusername fun\n"+user.getUsername()+"  "+user.getPassword()+"  "+user.getRole());
        Pages.customerid= user.getTypeId();
        if(user==null)
            throw new UsernameNotFoundException("Username not found");
        return new Login(user);

    }

}
