package com.example.cgw.JPAData;

import javax.persistence.*;
@Table
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="AREA" , nullable = false)
    private String area;
    @Column(name="CITY" , nullable = false)
    private String city;
    @Column(name="STATE" , nullable = false)
    private String state;
    @Column(name="PINCODE" , nullable = false)
    private String pincode;
    @Column(name="NAME" , nullable = false)
    private String name;
    @Column(name="PHONE" , nullable = false)
    private String phone;
    @Column(name="HOUSENUMBER")
    private String homenumber;
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Cust_id")
    private Customer customer;

    public Address(){}


    public Address(Integer id, String area, String city, String state, Customer p, String pincode, String phone, String name, String homenumber) {
        this.id = id;
        this.area = area;
        this.city = city;
        this.state = state;
        this.customer = p;
        this.pincode=pincode;
        this.name=name;
        this.phone=phone;
        this.homenumber = homenumber;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer p) {
        this.customer = p;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public String getHomenumber() {
        return homenumber;
    }

    public void setHomenumber(String homenumber) {
        this.homenumber = homenumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                " Ordered for = '" + name + '\'' +
                " House number = '" + homenumber + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", customer=" + customer +
                '}';
    }
}
