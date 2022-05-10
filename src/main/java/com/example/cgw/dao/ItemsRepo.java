package com.example.cgw.dao;

import com.example.cgw.JPAData.Items;
import com.example.cgw.JPAData.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemsRepo extends JpaRepository<Items,Integer> {
    public List<Items> findAllByPartner(Partner p);

    @Query("from Items where partner=?1 and item_name=?2")
    public Items findByShopAndItemName(Partner partner, String itemname);
    @Modifying
    @Query("update Items set qty=qty-?1 where partner=?2 and id=?3")
    public int decrementQuantity(int qty, Partner partner, int id);
    public Items findById(int id);
}
