package com.example.cgw.dao;

import com.example.cgw.JPAData.DistanceBetweenAreas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface DistanceBetweenAreasRepo extends JpaRepository<DistanceBetweenAreas, Integer> {
    public DistanceBetweenAreas findByFromLocationAndToLocation(String fromloc, String toloc);
}
