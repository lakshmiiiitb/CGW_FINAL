package com.example.cgw.JPAData;

import javax.persistence.*;

@Table
@Entity
public class DistanceBetweenAreas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="fromLocation" , nullable = false)
    private String fromLocation;
    @Column(name="toLocation" , nullable = false)
    private String toLocation;
    @Column(name = "Dist_in_kms", nullable = false)
    private int dist;

    public  DistanceBetweenAreas(){}

    public DistanceBetweenAreas(Integer id, String fromLocation, String toLocation,int distkms) {
        this.id = id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.dist=distkms;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int distkms) {
        this.dist = distkms;
    }

    @Override
    public String toString() {
        return "DistanceBetweenAreas{" +
                "id=" + id +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\''+
                ", Distance='" + dist + '\'' +
                '}';
    }
}
