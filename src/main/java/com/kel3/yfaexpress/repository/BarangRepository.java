package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Integer> {
    List<Barang> findAllByIdBarang(Integer idBarang);
}
