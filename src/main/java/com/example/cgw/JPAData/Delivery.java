package com.example.cgw.JPAData;

import javax.persistence.*;

@Entity
@Table
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    @Column(name="NAME" , nullable = false)
    private String name;
    @Column(name="email" , nullable = false)
    private String email;
    @Column(name="USERNAME" , nullable = false , unique = true)
    private String username;
    @Column(name="PASSWORD" , nullable = false)
    private String password;
    @Column(name="PHONE" , nullable = false)
    private String phone;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;


    public Delivery() {
    }
    public Delivery(String name, String phone, String email, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\''+
                '}';
    }
}
