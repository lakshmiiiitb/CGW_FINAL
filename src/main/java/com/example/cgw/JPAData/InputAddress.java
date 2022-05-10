package com.example.cgw.JPAData;

public class InputAddress {
    private String area;
    private String city;
    private String state;
    private String pincode;

    public InputAddress(){}

    public InputAddress(String area, String city, String state, String pin) {
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode= pin;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
