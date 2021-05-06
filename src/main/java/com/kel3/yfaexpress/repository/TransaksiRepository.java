package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
    List<Transaksi> findAllByStatusDeliveryEquals(String statusDeleivery);

    Transaksi findByResiEquals(String resi);
}
