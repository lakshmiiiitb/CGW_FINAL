package com.example.cgw.JPAData;

import javax.persistence.*;

@Entity
@Table
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String username,password;
    String role;
    int typeId;

    public UserCredentials(String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserCredentials(String username, String password, String role, int id) {

        this.username = username;
        this.password = password;
        this.role = role;
        this.typeId=id;
    }

    public UserCredentials(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type id='" + typeId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
