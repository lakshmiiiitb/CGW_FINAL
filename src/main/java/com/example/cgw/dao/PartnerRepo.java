package com.example.cgw.dao;

import com.example.cgw.JPAData.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PartnerRepo extends JpaRepository<Partner,Integer> {
    public Partner findByUsername(String uname);

    @Query("from Partner where storeLoc=?1 and type=?2")
    public List<Partner> findByStoreLoc(String loc, String type);

    public Partner findByStoreName(String store);
    public Partner findById(int id);

}
