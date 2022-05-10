package com.example.cgw.JPAData;

import javax.persistence.*;

@Entity
@Table
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="STATUS" , nullable = false)
    private char status; //o-ordereed, p-picked, d-delivered

    @Column(name="ITEM_NAME" , nullable = false)
    private String item_name;
    @Column(name="QTY" , nullable = false)
    private int qty;
    @Column(name="PRICE" , nullable = false)
    private double price;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private String image;

    @OneToOne
    Customer customer;

    @OneToOne
    Address address;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    Partner partner;

    @OneToOne
    Delivery delivery;

    public Orders(Cart cart, Address address, Partner partner, char Status, Delivery delivery) {

        this.item_name=cart.getItem_name();
        this.qty= cart.getQty();
        this.price= cart.getPrice() * cart.getQty();
        this.image= cart.getImage();
        this.customer=cart.getCustomer();

        this.address = address;
        this.partner = partner;
        this.status= Status;
        this.delivery=delivery;

    }

    public Orders(){}

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", status=" + status +
                ", item_name='" + item_name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", customer=" + customer +
                ", address=" + address +
                ", partner=" + partner +
                ", delivery=" + delivery +
                '}';
    }
}
