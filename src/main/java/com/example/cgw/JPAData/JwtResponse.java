package com.example.cgw.JPAData;

public class JwtResponse {
    String token;
    int customerid;
    String role;

    public JwtResponse(String token)
    {
        this.token=token;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", customerid=" + customerid +
                ", role='" + role + '\'' +
                '}';
    }

    public JwtResponse(String token, int id, String role)
    {
        this.token=token;
        this.role=role;
        this.customerid=id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
