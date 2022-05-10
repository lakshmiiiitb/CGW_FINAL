package com.example.cgw.JPAData;

public class LocationType {
    private String location;
    private String type;

    public LocationType(){}

    public LocationType(String location, String type) {
        this.location = location;
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LocationType{" +
                "location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
