package com.example.cgw.JPAData;

import javax.persistence.*;

@Entity
@Table
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="ITEM_NAME" , nullable = false)
    private String item_name;
    @Column(name="price" , nullable = false)
    private double price;
    @Column(name="qty" , nullable = false)
    private int qty;

    @Column(name="unit" , nullable = false)
    private String unit;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Partner partner;

    public Items(){}


    public Items(int id, String item_name, double price, int qty, Partner p , String image, String unit) {
        this.id = id;
        this.item_name = item_name;
        this.price = price;
        this.qty = qty;
        this.partner = p;
        this.image=image;

        this.unit=unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner p) {
        this.partner = p;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Items{" +
                "item_name='" + item_name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", partner=" + partner +
                ", Image=" + image+
                ", Unit=" + unit+
                '}';
    }
}
