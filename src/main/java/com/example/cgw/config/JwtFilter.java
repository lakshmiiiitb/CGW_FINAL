package com.example.cgw.config;

import com.example.cgw.Helper.JwtUtil;
import com.example.cgw.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal");
        //get header
        System.out.println(request.getHeaderNames());
        System.out.println(response);
        String authorization_header = request.getHeader("Authorization");
        System.out.println("auth header= "+authorization_header);
        String username=null;
        //check if header is valid
        if(authorization_header!=null && authorization_header.startsWith("Bearer "))
        {
            //take token
            String token=authorization_header.substring(7);
            //check if its valid
            System.out.println("token = "+token);
            try{
                username= this.jwtUtil.extractUsername(token);
                System.out.println("username extracted from token = "+username);

            }catch (Exception e){
                System.out.println("Username not found");
            }
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
            System.out.println("userdetails: "+userDetails.getUsername()+"  auth: "+userDetails.getAuthorities()+" pass:  "+userDetails.getPassword());
            System.out.println("SecurityContextHolder.getContext().getAuthentication() = "+SecurityContextHolder.getContext().getAuthentication());
            if(username!=null )
            {
                if(jwtUtil.validateToken(token,userDetails))
                {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }

            }
            else
                System.out.println("Username is null");

        }
        else
        {
            System.out.println("Header is null");
        }
        System.out.println("end of do filter");
        filterChain.doFilter(request,response);//go ahead for api
        //safe to keep this statement outside first if. if you place it inside if, then only for auth links, it goes forward
        //for non auth apps, it stucks, as first if fails, and it executes else.
    }
}
