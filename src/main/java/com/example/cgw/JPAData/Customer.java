package com.example.cgw.JPAData;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="NAME" , nullable = false)
    private String name;
    @Column(name="EMAIL" , nullable = false)
    private String email;
    @Column(name="USERNAME" , nullable = false,unique = true)
    private String username;
    @Column(name="PASSWORD" , nullable = false)
    private String password;
    @Column(name="PHONE" , nullable = false)
    private String phone;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Address> addresslist;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Cart> cartSet;

    public Customer() {
    }
    public Customer(String name, String phone, String email, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Set<Cart> getCartSet() {
        return cartSet;
    }

    public void setCartSet(Set<Cart> cartSet) {
        this.cartSet = cartSet;
    }

    public Set<Address> getAddresslist() {
        return addresslist;
    }

    public void setAddresslist(Set<Address> addresslist) {
        this.addresslist = addresslist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
