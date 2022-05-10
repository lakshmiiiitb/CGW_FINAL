package com.example.cgw.JPAData;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;
    @Column(name="STORENAME" , nullable = false)
    private String storeName;
    @Column(name="STORELOC" , nullable = false)
    private String storeLoc;
    @Column(name="USERNAME" , nullable = false, unique = true)
    private String username;
    @Column(name="PASSWORD" , nullable = false)
    private String password;
    @Column(name="contactno" , nullable = false)
    private String contactno;
    @Column(name="email" , nullable = false)
    private String email;
    @Column(name="description")
    private String description;
    @Column(name="shoptype")
    private String type;
    @Column(name="City")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="pincode")
    private String pincode;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Items> itemsSet;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Orders> ordersSet;

    public Partner() {
    }

    public Partner(String storeName, String storeLoc, String username, String password, String contactno,String email) {
        this.storeName = storeName;
        this.storeLoc = storeLoc;
        this.username = username;
        this.password = password;
        this.contactno = contactno;
        this.email=email;
    }

    public Partner(String storeName, String storeLoc, String username, String password, String contactno, String email, String description, String type, String city, String state, String pincode, String image) {
        this.storeName = storeName;
        this.storeLoc = storeLoc;
        this.username = username;
        this.password = password;
        this.contactno = contactno;
        this.email = email;
        this.description = description;
        this.type = type;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.image = image;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    public Set<Items> getItemsSet() {
        return itemsSet;
    }

    public void setItemsSet(Set<Items> itemsSet) {
        this.itemsSet = itemsSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLoc() {
        return storeLoc;
    }

    public void setStoreLoc(String storeLoc) {
        this.storeLoc = storeLoc;
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

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", storeLoc='" + storeLoc + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contactno='" + contactno + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", image='" + image + '\'' +
                ", itemsSet=" + itemsSet +
                ", ordersSet=" + ordersSet +
                '}';
    }
}
