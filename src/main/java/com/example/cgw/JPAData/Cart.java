package com.example.cgw.JPAData;

import javax.persistence.*;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="ITEM_NAME" , nullable = false)
    private String item_name;
    @Column(name="QTY" , nullable = false)
    private int qty;
    @Column(name="PRICE" , nullable = false)
    private double price;
    @Column(name="ITEMID" , nullable = false)
    private int itemid;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    Customer customer;

    public Cart(){}

    public Cart(String item_name, int qty, double price, String image, Customer customer, int itemid) {

        this.item_name = item_name;
        this.qty = qty;
        this.price = price;
        this.image = image;
        this.customer = customer;
        this.itemid=itemid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", item_id='" + itemid + '\'' +
                ", item_name='" + item_name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", customer=" + customer +
                '}';
    }
}
